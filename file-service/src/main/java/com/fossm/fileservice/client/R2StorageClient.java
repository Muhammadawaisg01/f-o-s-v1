package com.fossm.fileservice.client;

import com.fossm.fileservice.configuration.properties.R2Properties;
import com.fossm.fileservice.dto.FilePartDto;
import com.fossm.fileservice.dto.response.DownloadUrlResponse;
import com.fossm.fileservice.dto.response.UploadUrlResponse;
import com.fossm.fileservice.model.FileMetadata;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CompleteMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.CompletedPart;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedUploadPartRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class R2StorageClient {

  private final R2Properties r2Properties;

  private final S3Presigner r2Presigner;

  private final S3Client r2Client;

  public String createMultipartUpload(FileMetadata metadata) {
    var uploadResponse = r2Client.createMultipartUpload(
        request -> request.bucket(r2Properties.getVerificationBucketName())
            .key(metadata.getId().toString())
    );
    return uploadResponse.uploadId();
  }

  public List<UploadUrlResponse> getUploadUrl(FileMetadata metadata, List<FilePartDto> uploadUrlRequests) {
    return uploadUrlRequests.stream()
        .parallel()
        .map(request ->
            UploadUrlResponse.builder()
                .fileMetadataId(metadata.getId())
                .uploadId(metadata.getUploadId())
                .partNumber(request.partNumber())
                .presignedUrl(getPresignedUrl(metadata, request).toString())
                .build()
        )
        .toList();
  }

  public UploadUrlResponse getUploadUrl(FileMetadata metadata) {
    return UploadUrlResponse.builder()
        .fileMetadataId(metadata.getId())
        .presignedUrl(getPresignedUrl(metadata).toString())
        .build();
  }

  public void completeUploads(FileMetadata metadata, List<FilePartDto> fileParts) {
    var completedParts = fileParts.stream()
        .parallel()
        .map(
            part -> CompletedPart.builder()
                .partNumber(part.partNumber())
                .eTag(part.eTag())
                .build()
        )
        .toList();

    CompleteMultipartUploadResponse response = r2Client
        .completeMultipartUpload(request -> request
            .uploadId(metadata.getUploadId())
            .bucket(metadata.getBucket())
            .key(metadata.getId().toString())
            .multipartUpload(upload -> upload.parts(completedParts))
        );

    log.info("Complete multipart upload. Response: {}", response);
  }

  public DownloadUrlResponse getDownloadUrl(FileMetadata metadata) {
    var presignedGetObjectRequest = r2Presigner.presignGetObject(
        request -> request.getObjectRequest(
                presignedRequest -> presignedRequest
                    .bucket(metadata.getBucket())
                    .key(metadata.getId().toString())
            )
            .signatureDuration(Duration.ofSeconds(r2Properties.getDownloadSignatureDurationSeconds()))
    );

    log.info("Providing presigned download URL, request: {}", presignedGetObjectRequest);

    return DownloadUrlResponse.builder()
        .uploadId(metadata.getUploadId())
        .downloadUrl(presignedGetObjectRequest.url().toString())
        .build();
  }

  private URL getPresignedUrl(FileMetadata metadata, FilePartDto part) {
    PresignedUploadPartRequest partRequest =
        r2Presigner.presignUploadPart(request -> request
            .uploadPartRequest(uploadPartRequest -> uploadPartRequest
                .uploadId(metadata.getUploadId())
                .bucket(metadata.getBucket())
                .partNumber(part.partNumber())
                .key(metadata.getId().toString())
            )
            .signatureDuration(Duration.ofSeconds(r2Properties.getUploadSignatureDurationSeconds()))
        );

    return partRequest.url();
  }

  private URL getPresignedUrl(FileMetadata metadata) {
    var putRequest = r2Presigner.presignPutObject(
        request -> request.putObjectRequest(
                putObjectRequest -> putObjectRequest
                    .bucket(metadata.getBucket())
                    .key(metadata.getId().toString())
            )
            .signatureDuration(Duration.ofSeconds(r2Properties.getUploadSignatureDurationSeconds()))
    );

    return putRequest.url();
  }

}

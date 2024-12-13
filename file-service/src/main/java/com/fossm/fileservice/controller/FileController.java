package com.fossm.fileservice.controller;

import com.fossm.fileservice.dto.FileMetadataDto;
import com.fossm.fileservice.dto.FilePartDto;
import com.fossm.fileservice.dto.request.UploadRequest;
import com.fossm.fileservice.dto.response.DownloadUrlResponse;
import com.fossm.fileservice.dto.response.UploadUrlResponse;
import com.fossm.fileservice.facade.FileFacade;
import com.fossm.swagger.controller.CommonController;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController implements CommonController {

  private final FileFacade fileFacade;

  @Operation(summary = "Create multipart upload", description = "Creates multipart upload and returns uploadId")
  @PostMapping("/multipart-file")
  public FileMetadataDto createMultipartUpload(@RequestBody UploadRequest request) {
    return fileFacade.createMultipartUpload(request);
  }

  @Operation(summary = "Retrieve presigned URLs for upload (multipart)",
      description = "Returns a list of presigned URLs for multipart file upload")
  @PostMapping("/multipart-file/{fileMetadataId}/presigned-url")
  public List<UploadUrlResponse> getUploadPresignedUrls(@PathVariable UUID fileMetadataId,
      @RequestBody List<FilePartDto> partNumbers) {
    return fileFacade.getUploadPresignedUrl(fileMetadataId, partNumbers);
  }

  @Operation(summary = "Retrieve presigned URL for upload (single file up to 100MB)",
      description = "Returns presigned URL for uploading a single file")
  @PostMapping("/single-file")
  public UploadUrlResponse getUploadPresignedUrl(@RequestBody UploadRequest request) {
    return fileFacade.getUploadPresignedUrl(request);
  }

  @Operation(summary = "Complete multipart upload", description = "Completes the process of multipart file upload")
  @PostMapping("/multipart-file/{fileMetadataId}/complete")
  public void completeMultipartUpload(@PathVariable UUID fileMetadataId,
      @RequestBody List<FilePartDto> completedParts) {
    fileFacade.completeMultipartUpload(fileMetadataId, completedParts);
  }

  @Operation(summary = "Get presigned URL for download", description = "Returns presigned URL by metadata id")
  @GetMapping("/{fileMetadataId}")
  public DownloadUrlResponse getDownloadPresignedUrl(@PathVariable UUID fileMetadataId) {
    return fileFacade.getDownloadPresignedUrl(fileMetadataId);
  }

}
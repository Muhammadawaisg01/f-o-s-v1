package com.fossm.fileservice.facade;

import com.fossm.exception.common.NotFoundException;
import com.fossm.fileservice.client.R2StorageClient;
import com.fossm.fileservice.configuration.properties.R2Properties;
import com.fossm.fileservice.dto.FileMetadataDto;
import com.fossm.fileservice.dto.FilePartDto;
import com.fossm.fileservice.dto.request.UploadRequest;
import com.fossm.fileservice.dto.response.DownloadUrlResponse;
import com.fossm.fileservice.dto.response.UploadUrlResponse;
import com.fossm.fileservice.mapper.FileMetadataMapper;
import com.fossm.fileservice.model.FileMetadata;
import com.fossm.fileservice.service.FileService;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileFacade {

  private final FileService fileService;

  private final R2Properties r2Properties;

  private final R2StorageClient storageClient;

  private final FileMetadataMapper fileMetadataMapper;

  @Transactional
  public FileMetadataDto createMultipartUpload(UploadRequest request) {
    var metadata = fileMetadataMapper.merge(request, new FileMetadata());
    metadata = fileService.saveFileMetaData(metadata);
    var uploadId = storageClient.createMultipartUpload(metadata);
    metadata.setUploadId(uploadId);
    metadata.setBucket(r2Properties.getVerificationBucketName());
    metadata = fileService.saveFileMetaData(metadata);
    return fileMetadataMapper.toDto(metadata);
  }

  @Transactional
  public List<UploadUrlResponse> getUploadPresignedUrl(UUID fileMetadataId, List<FilePartDto> partNumbers) {
    var metadata = fileService.getFileMetaData(fileMetadataId)
        .orElseThrow(() -> new NotFoundException("File metadata not found"));
    return storageClient.getUploadUrl(metadata, partNumbers);
  }

  @Transactional
  public UploadUrlResponse getUploadPresignedUrl(UploadRequest request) {
    var metadata = fileMetadataMapper.merge(request, new FileMetadata());
    metadata = fileService.saveFileMetaData(metadata);
    var uploadId = storageClient.createMultipartUpload(metadata);
    metadata.setUploadId(uploadId);
    metadata.setBucket(r2Properties.getVerificationBucketName());
    metadata = fileService.saveFileMetaData(metadata);
    return storageClient.getUploadUrl(metadata);
  }

  public void completeMultipartUpload(UUID fileMetadataId, List<FilePartDto> completedParts) {
    var metadata = fileService.getFileMetaData(fileMetadataId)
        .orElseThrow(() -> new NotFoundException("File metadata not found"));
    storageClient.completeUploads(metadata, completedParts);
    fileService.saveFileMetaData(metadata);
  }

  public DownloadUrlResponse getDownloadPresignedUrl(UUID fileMetadataId) {
    var metadata = fileService.getFileMetaData(fileMetadataId)
        .orElseThrow(() -> new NotFoundException("File metadata not found"));
    return storageClient.getDownloadUrl(metadata);
  }

}

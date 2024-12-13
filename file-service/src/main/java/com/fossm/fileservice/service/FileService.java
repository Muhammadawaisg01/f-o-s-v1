package com.fossm.fileservice.service;

import com.fossm.fileservice.model.FileMetadata;
import com.fossm.fileservice.repository.FileMetadataRepository;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

  private final FileMetadataRepository fileMetadataRepository;

  public FileMetadata saveFileMetaData(FileMetadata fileMetadata) {
    return fileMetadataRepository.save(fileMetadata);
  }

  public Optional<FileMetadata> getFileMetaData(UUID fileMetadataId) {
    return fileMetadataRepository.findById(fileMetadataId);
  }
}

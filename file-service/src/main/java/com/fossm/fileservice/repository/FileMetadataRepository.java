package com.fossm.fileservice.repository;

import com.fossm.fileservice.model.FileMetadata;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetadataRepository extends JpaRepository<FileMetadata, UUID> {

  Optional<FileMetadata> findByUploadId(String uploadId);

}

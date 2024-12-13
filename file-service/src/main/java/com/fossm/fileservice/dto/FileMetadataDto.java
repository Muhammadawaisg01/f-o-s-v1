package com.fossm.fileservice.dto;

import com.fossm.fileservice.model.enums.UploadStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.fossm.fileservice.model.FileMetadata}
 */
public record FileMetadataDto(
    UUID id,
    LocalDateTime updatedAt,
    LocalDateTime createdAt,
    String uploadId,
    @NotNull
    UUID userId,
    @NotBlank
    String filename,
    @NotBlank
    String filetype,
    String relativePath,
    UploadStatus uploadStatus
) implements Serializable {

}
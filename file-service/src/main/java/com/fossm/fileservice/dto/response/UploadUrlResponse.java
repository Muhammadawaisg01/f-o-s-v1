package com.fossm.fileservice.dto.response;

import java.io.Serializable;
import java.util.UUID;
import lombok.Builder;

@Builder

public record UploadUrlResponse(
    UUID fileMetadataId,
    String uploadId,
    Integer partNumber,
    String presignedUrl
) implements Serializable {

}

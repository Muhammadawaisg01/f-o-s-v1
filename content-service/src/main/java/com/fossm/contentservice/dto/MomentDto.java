package com.fossm.contentservice.dto;

import com.fossm.contentservice.model.enums.MediaType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.fossm.contentservice.model.Moment}
 */
public record MomentDto(
    UUID id,
    LocalDateTime createdAt,
    UUID userId,
    MediaType mediaType,
    String mediaUrl,
    Long viewCount,
    LocalDateTime expirationDate
) implements Serializable {

}

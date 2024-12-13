package com.fossm.contentservice.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.fossm.contentservice.model.Comment}
 */
public record CommentDto(
    UUID id,
    LocalDateTime createdAt,
    UUID userId,
    String text,
    boolean liked
) implements Serializable {

}

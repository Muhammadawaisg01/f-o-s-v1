package com.fossm.contentservice.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.fossm.contentservice.model.CommentReaction}
 */
public record CommentReactionDto(
    UUID id,
    LocalDateTime createdAt,
    UUID userId
) implements Serializable {

}

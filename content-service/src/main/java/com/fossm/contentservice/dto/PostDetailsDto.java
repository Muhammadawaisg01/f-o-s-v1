package com.fossm.contentservice.dto;

import com.fossm.contentservice.model.enums.ContentCategory;
import com.fossm.contentservice.model.enums.ContentWarning;
import com.fossm.contentservice.model.enums.MediaType;
import com.fossm.contentservice.model.enums.RelationType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;

/**
 * Detailed DTO for {@link com.fossm.contentservice.model.Post}
 */
@Deprecated
@Builder
public record PostDetailsDto(
    UUID id,
    LocalDateTime createdAt,
    UUID userId,
    String username,
    MediaType mediaType,
    String description,
    RelationType relationType,
    boolean ageLimited,
    Set<ContentWarning> warnings,
    Set<ContentCategory> categories,
    List<ContentDto> contents,
    boolean doubleMonetization,
    BigDecimal price,
    boolean allowComments,
    Set<String> hashtags,
    long viewCount,
    long commentCount,
    long shareCount,
    long reactionCount,
    boolean liked
) implements Serializable {

}

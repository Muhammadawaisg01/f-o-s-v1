package com.fossm.contentservice.dto;

import com.fossm.contentservice.dto.user.AvatarProfileDto;
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

/**
 * DTO for {@link com.fossm.contentservice.model.Post}
 */
public record PostDto(
    UUID id,
    LocalDateTime createdAt,
    AvatarProfileDto avatarProfile,
    MediaType mediaType,
    String description,
    Set<String> hashtags,
    List<ContentDto> contents,
    RelationType relationType,
    Set<ContentCategory> categories,
    boolean ageLimited,
    Set<ContentWarning> warnings,
    boolean doubleMonetization,
    BigDecimal price,
    long viewCount,
    long commentCount,
    long shareCount,
    long reactionCount,
    boolean liked
) implements Serializable {

}

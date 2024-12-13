package com.fossm.contentservice.dto;

import com.fossm.contentservice.model.enums.ContentCategory;
import com.fossm.contentservice.model.enums.ContentWarning;
import com.fossm.contentservice.model.enums.MediaType;
import com.fossm.contentservice.model.enums.RelationType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record PostPreviewDto(
    UUID id,
    LocalDateTime createdAt,
    UUID userId,
    MediaType mediaType,
    String thumbnailUrl,
    RelationType relationType,
    boolean ageLimited,
    Set<ContentWarning> warnings,
    boolean doubleMonetization,
    BigDecimal price
) implements Serializable {

}

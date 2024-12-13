package com.fossm.contentservice.dto.request;

import com.fossm.contentservice.model.enums.ContentCategory;
import com.fossm.contentservice.model.enums.RelationType;
import com.fossm.contentservice.validation.annotations.Hashtag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public record PostUpdateRequest(
    @Size(max = 500)
    String description,
    RelationType relationType,
    @Valid
    AgeWarningDto ageWarning,
    @Size(min = 1, max = 6)
    Set<ContentCategory> categories,
    @Valid
    DoubleMonetizationDto doubleMonetization,
    @Size(max = 6)
    Set<@Hashtag String> hashtags,
    Boolean allowComments
) implements Serializable {

}
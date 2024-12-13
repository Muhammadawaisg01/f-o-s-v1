package com.fossm.contentservice.dto.request;

import com.fossm.contentservice.model.enums.ContentCategory;
import com.fossm.contentservice.model.enums.MediaType;
import com.fossm.contentservice.model.enums.RelationType;
import com.fossm.contentservice.validation.annotations.Hashtag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public record PostCreateRequest(
    @NotNull
    MediaType mediaType,
    @Size(max = 500)
    String description,
    @NotNull
    RelationType relationType,
    @NotNull
    @Valid
    AgeWarningDto ageWarning,
    @NotNull
    @Size(min = 1, max = 6)
    Set<ContentCategory> categories,
    @Valid
    DoubleMonetizationDto doubleMonetization,
    @Size(max = 6)
    Set<@Hashtag String> hashtags,
    List<ContentRequest> contents,
    Boolean allowComments
) implements Serializable {

}
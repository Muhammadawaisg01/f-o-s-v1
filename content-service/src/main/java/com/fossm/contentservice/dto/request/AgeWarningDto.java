package com.fossm.contentservice.dto.request;

import com.fossm.contentservice.model.enums.ContentWarning;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public record AgeWarningDto(
    boolean ageLimited,
    Set<ContentWarning> warnings
) {

  @JsonIgnore
  @AssertTrue(message = "If `ageLimited` is true, `warnings` must not be null or empty; otherwise not")
  public boolean isContentWarningStateValidForAgeLimit() {
    return ageLimited
        ? nonNull(warnings)
        : isNull(warnings);
  }

}
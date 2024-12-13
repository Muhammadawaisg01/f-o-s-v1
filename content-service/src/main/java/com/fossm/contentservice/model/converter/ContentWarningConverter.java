package com.fossm.contentservice.model.converter;

import com.fossm.contentservice.model.enums.ContentWarning;

import jakarta.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class ContentWarningConverter implements AttributeConverter<Set<ContentWarning>, String> {

  @Override
  public String convertToDatabaseColumn(Set<ContentWarning> contentWarnings) {
    return contentWarnings != null ? StringUtils.join(contentWarnings, ",") : null;
  }

  @Override
  public Set<ContentWarning> convertToEntityAttribute(String s) {
    return s == null ? null : Arrays.stream(
            s.split(","))
        .map(String::strip)
        .map(ContentWarning::valueOf)
        .collect(Collectors.toSet());
  }

}

package com.fossm.contentservice.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public record DoubleMonetizationDto(
    boolean doubleMonetization,
    @DecimalMin("4.99")
    BigDecimal price
) {

  @JsonIgnore
  @AssertTrue(message = "If `doubleMonetization` is true, `price` must not be null; otherwise not")
  public boolean isPriceValidForMonetization() {
    return doubleMonetization
        ? nonNull(price)
        : isNull(price);
  }

}
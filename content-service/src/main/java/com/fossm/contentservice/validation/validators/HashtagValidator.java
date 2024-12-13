package com.fossm.contentservice.validation.validators;

import com.fossm.contentservice.validation.annotations.Hashtag;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HashtagValidator implements ConstraintValidator<Hashtag, String> {

  @Override
  public void initialize(Hashtag constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && value.matches("^[\\p{L}\\p{N}\\p{M}_]{1,30}$");
  }

}

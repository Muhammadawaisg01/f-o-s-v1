package com.fossm.contentservice.validation.validators;

import com.fossm.contentservice.validation.annotations.Username;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

  @Override
  public void initialize(Username constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && value.matches("^[\\p{L}\\p{M}\\p{S}\\p{N}\\p{P}]{1,100}$");
  }

}

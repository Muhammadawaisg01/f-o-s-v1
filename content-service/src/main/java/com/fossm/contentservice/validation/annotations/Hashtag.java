package com.fossm.contentservice.validation.annotations;

import com.fossm.contentservice.validation.validators.HashtagValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = HashtagValidator.class)
@Target({FIELD, TYPE, TYPE_USE})
@Retention(RUNTIME)
public @interface Hashtag {

  String message() default "Invalid hashtag: 30 chars max, only letters, numbers, emojis and underscores (_) allowed";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}

package com.fossm.userservice.exception;

import com.fossm.exception.common.ServerException;

import java.util.Map;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ServerException {

  public BadRequestException(String message) {
    super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), message);
  }

  public BadRequestException(HttpStatus httpStatus, String code, String message) {
    super(httpStatus, code, message);
  }

  public BadRequestException(HttpStatus httpStatus, String code, String message, Map<String, String> details) {
    super(httpStatus, code, message, details);
  }
}

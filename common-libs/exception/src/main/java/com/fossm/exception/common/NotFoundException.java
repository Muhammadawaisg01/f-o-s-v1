package com.fossm.exception.common;

import com.fossm.exception.util.ExceptionCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ServerException {

  public NotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, ExceptionCode.NOT_FOUND_ERROR, message);
  }

  public NotFoundException(String code, String message) {
    super(HttpStatus.NOT_FOUND, code, message);
  }
}
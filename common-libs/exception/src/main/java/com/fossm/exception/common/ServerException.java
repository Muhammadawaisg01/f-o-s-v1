package com.fossm.exception.common;

import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ServerException extends RuntimeException {

  String code;
  HttpStatus httpStatus;
  Map<String, String> details;

  public ServerException(HttpStatus httpStatus, String code, String message) {
    super(message);
    this.httpStatus = httpStatus;
    this.code = code;
    this.details = null;
  }

  public ServerException(HttpStatus httpStatus, String code, String message,
      Map<String, String> details) {
    super(message);
    this.httpStatus = httpStatus;
    this.code = code;
    this.details = details;
  }
}
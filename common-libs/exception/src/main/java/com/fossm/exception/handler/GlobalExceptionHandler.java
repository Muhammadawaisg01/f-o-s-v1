package com.fossm.exception.handler;

import com.fossm.exception.common.ServerException;
import com.fossm.exception.dto.ExceptionResponse;
import com.fossm.exception.util.Constants;
import com.fossm.exception.util.ExceptionCode;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ServerException.class)
  public ResponseEntity<Object> handleServerException(
      ServerException exception,
      WebRequest request
  ) {
    return handleException(exception, exception.getHttpStatus(), request);
  }

  private ResponseEntity<Object> handleException(
      Exception exception,
      HttpStatus status,
      WebRequest request
  ) {
    ExceptionResponse response = getExceptionResponse(exception, status);
    return handleExceptionInternal(exception, response, new HttpHeaders(), status, request);
  }

  private ExceptionResponse getExceptionResponse(Exception exception, HttpStatus status) {
    return ExceptionResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(status.value())
        .code(getExceptionCode(exception))
        .details(getExceptionDetails(exception))
        .build();
  }

  private Map<String, String> getExceptionDetails(Exception exception) {
    if (exception instanceof BindException bindException) {
      return bindException.getBindingResult().getFieldErrors().stream()
          .filter(fieldError -> Objects.nonNull(fieldError.getDefaultMessage()))
          .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
              (key, duplicateKey) -> key));
    }

    return Objects.nonNull(exception.getMessage())
        ? Map.of(Constants.MESSAGE_KEY, exception.getMessage())
        : null;
  }

  private String getExceptionCode(Exception exception) {
    if (exception instanceof BindException) {
      return ExceptionCode.VALIDATION_ERROR;
    }
    if (exception instanceof ServerException serverException) {
      return serverException.getCode();
    }
    return ExceptionCode.SERVER_ERROR;
  }

}
package com.codebotx.security.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.codebotx.security.exception.exception.ErrorMessage;
import com.codebotx.security.exception.exception.SignInException;
import com.codebotx.security.exception.exception.TokenRefreshException;

import java.util.Date;

@RestControllerAdvice
public class AuthControllerAdvice {


    @ExceptionHandler(value = SignInException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleLoginException(SignInException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }
}

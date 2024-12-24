
package com.codebotx.api_gateway.exception.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.codebotx.api_gateway.exception.CustomException;


@ControllerAdvice
public class AuthFilterAdvice {


    @ExceptionHandler
    public ResponseEntity<CustomException> handleCustomServiceException(Exception ex) {
        CustomException error = new CustomException(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    
    // @ExceptionHandler(value = SignInException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public ErrorMessage handleLoginException(SignInException ex, WebRequest request) {
    //     return new ErrorMessage(
    //             HttpStatus.BAD_REQUEST.value(),
    //             new Date(),
    //             ex.getMessage(),
    //             request.getDescription(false)
    //     );
    // }

    // @ExceptionHandler(value = TokenRefreshException.class)
    // @ResponseStatus(HttpStatus.FORBIDDEN)
    // public ErrorMessage handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
    //     return new ErrorMessage(
    //             HttpStatus.FORBIDDEN.value(),
    //             new Date(),
    //             ex.getMessage(),
    //             request.getDescription(false)
    //     );
    // }
}

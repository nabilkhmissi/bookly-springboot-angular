package com.springboot.libraryappmongo.exceptionHandlers;

import com.springboot.libraryappmongo.exception.AccountNotActiveException;
import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorResponse> handleException(InvalidEntityException exception, WebRequest webRequest){
        final ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDate.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .errors(exception.getErrors())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException exception, WebRequest webRequest){
        final ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDate.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotActiveException.class)
    public ResponseEntity<ErrorResponse> handleException(AccountNotActiveException exception, WebRequest webRequest){
        final ErrorResponse response = ErrorResponse.builder()
                .timestamp(LocalDate.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

package com.springboot.libraryappmongo.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidEntityException extends RuntimeException{

    List<String> errors;
    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(List<String> errors){
        this.errors = errors;
    }

    public InvalidEntityException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityException(Throwable cause) {
        super(cause);
    }

    protected InvalidEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

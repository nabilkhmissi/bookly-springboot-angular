package com.springboot.libraryappmongo.exception;

public class AccountNotActiveException extends RuntimeException{

    public AccountNotActiveException(String message) {
        super(message);
    }
}

package com.example.book.exception;


public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}

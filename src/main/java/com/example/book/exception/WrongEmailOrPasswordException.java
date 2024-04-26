package com.example.book.exception;

public class WrongEmailOrPasswordException extends RuntimeException {
    public WrongEmailOrPasswordException(String exceptionMessage) {
        super(exceptionMessage);
    }
}

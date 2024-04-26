package com.example.book.exception;

public class WrongTokenOrExpiredException extends RuntimeException {

    public WrongTokenOrExpiredException(String exceptionMessage) {
        super(exceptionMessage);
    }
}

package com.example.book.exception;

import jakarta.persistence.EntityNotFoundException;

public class BookNotFoundException extends EntityNotFoundException {
    public BookNotFoundException(String message) {
        super(message);
    }
}

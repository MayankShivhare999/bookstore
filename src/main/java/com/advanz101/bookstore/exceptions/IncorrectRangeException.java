package com.advanz101.bookstore.exceptions;

public class IncorrectRangeException extends RuntimeException{

    public IncorrectRangeException(String message) {
        super(message);
    }

    public IncorrectRangeException() {
        super();
    }
}

package com.advanz101.bookstore.exceptions;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(){
        super();
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}

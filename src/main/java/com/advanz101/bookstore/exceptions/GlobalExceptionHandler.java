package com.advanz101.bookstore.exceptions;

import com.advanz101.bookstore.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionResponse> BookNotFoundExceptionHandler(Exception ex) {
        ExceptionResponse response = ExceptionResponse.builder().status(HttpStatus.NOT_FOUND).message(ex.getMessage()).build();
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(IncorrectRangeException.class)
    public ResponseEntity<ExceptionResponse> InCorrectRangeExceptionHandler(Exception ex) {
        ExceptionResponse response = ExceptionResponse.builder().status(HttpStatus.BAD_REQUEST).message(ex.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

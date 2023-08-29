package com.advanz101.bookstore.controller;

import com.advanz101.bookstore.entity.Book;
import com.advanz101.bookstore.exceptions.BookNotFoundException;
import com.advanz101.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.status(201).body(bookService.saveBook(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> fetchBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> fetchBookById(@PathVariable String bookId) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable String bookId, @RequestBody Book book) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.updateBook(bookId, book));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable String bookId) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.deleteBook(bookId));
    }

    @GetMapping("/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Book>> getBookWithRange(@PathVariable String minPrice, @PathVariable String maxPrice) {
        return ResponseEntity.ok(bookService.getBooksWithRange(Double.parseDouble(minPrice), Double.parseDouble(maxPrice)));
    }
}

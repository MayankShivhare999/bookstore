package com.advanz101.bookstore.services;

import com.advanz101.bookstore.entity.Book;
import com.advanz101.bookstore.exceptions.BookNotFoundException;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    List<Book> getBooks();

    Book getBookById(String bookId) throws BookNotFoundException;

    Book updateBook(String bookId, Book book) throws BookNotFoundException;

    Book deleteBook(String bookId) throws BookNotFoundException;

    List<Book> getBooksWithRange(double min, double max);
}

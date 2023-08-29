package com.advanz101.bookstore.services;

import com.advanz101.bookstore.entity.Book;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    List<Book> getBooks();

    Book getBookById(String bookId);

    Book updateBook(String bookId, Book book);

    Book deleteBook(String bookId);
}

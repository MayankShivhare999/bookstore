package com.advanz101.bookstore.services.impl;

import com.advanz101.bookstore.entity.Book;
import com.advanz101.bookstore.exceptions.BookNotFoundException;
import com.advanz101.bookstore.repositories.BookRespository;
import com.advanz101.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRespository bookRespository;

    @Autowired
    BookServiceImpl(BookRespository bookRespository) {
        this.bookRespository = bookRespository;
    }

    @Override
    public Book saveBook(Book book) {
        UUID uuid = UUID.randomUUID();
        book.setId(uuid.toString());
        return bookRespository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookRespository.findAll();
    }

    @Override
    public Book getBookById(String bookId) {
        return bookRespository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book Not Found with Id: "+bookId));
    }

    @Override
    public Book updateBook(String bookId, Book book) {
        if(!bookRespository.existsById(bookId))
            throw new BookNotFoundException("Book Not Found with Id: "+bookId);
        book.setId(bookId);
        return bookRespository.save(book);
    }

    @Override
    public Book deleteBook(String bookId) {
        Book book = bookRespository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book Not Found with Id: "+bookId));
        bookRespository.deleteById(bookId);
        return book;
    }
}
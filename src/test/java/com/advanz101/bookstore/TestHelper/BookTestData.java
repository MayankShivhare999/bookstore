package com.advanz101.bookstore.TestHelper;

import com.advanz101.bookstore.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookTestData {

    public static Book getBook() {
        Book book = new Book();
        String bookId = UUID.randomUUID().toString();
        book.setId(bookId);
        book.setTitle("title");
        book.setAuthor("author");
        book.setPrice(100.00);

        return book;
    }

    public static List<Book> getBooks() {
        Book b1 = new Book(UUID.randomUUID().toString(), "testTitle1", 191.00, "authortest1");
        Book b2 = new Book(UUID.randomUUID().toString(), "testTitle2", 192.00, "authortest2");
        Book b3 = new Book(UUID.randomUUID().toString(), "testTitle1", 193.00, "authortest3");

        List<Book> books = new ArrayList<>();
        books.add(b1);
        books.add(b2);
        books.add(b3);
        return books;
    }
}

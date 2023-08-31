package com.advanz101.bookstore.services.impl;

import com.advanz101.bookstore.entity.Book;
import com.advanz101.bookstore.exceptions.BookNotFoundException;
import com.advanz101.bookstore.exceptions.IncorrectRangeException;
import com.advanz101.bookstore.repositories.BookRepository;
import com.advanz101.bookstore.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Saves a new book in DB.
     *
     * @param book - the book entity to be saved in DB.
     * @return the saved book entity
     */
    @Override
    public Book saveBook(Book book) {
        UUID uuid = UUID.randomUUID();
        book.setId(uuid.toString());
        logger.info("id : {} generated", uuid);
        return bookRepository.save(book);
    }

    /**
     * Fetch All books from DB.
     *
     * @return list of books
     */
    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * fetch books for given bookId
     *
     * @param bookId
     * @return Book with matching bookId
     * @throws BookNotFoundException If book is not found with given bookId
     */
    @Override
    public Book getBookById(String bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new BookNotFoundException("Book Not Found with Id: "+bookId));
    }

    /**
     * updates Book Details for given bookId
     *
     * @param bookId - book which needs to be updated.
     * @param book - book data
     * @return - updated book entity
     * @throws BookNotFoundException If book is not found with given bookId
     */
    @Override
    public Book updateBook(String bookId, Book book) throws BookNotFoundException {
        if(!bookRepository.existsById(bookId)) {
            logger.info("Book not found with bookId : {}", bookId);
            throw new BookNotFoundException("Book Not Found with Id: "+bookId);
        }
        book.setId(bookId);
        return bookRepository.save(book);
    }

    /**
     * Deletes Book with given bookId
     *
     * @param bookId
     * @return Book, which is deleted from DB
     * @throws BookNotFoundException If book is not found with given bookId
     */
    @Override
    public Book deleteBook(String bookId) throws BookNotFoundException {
        if(!bookRepository.existsById(bookId)) {
            logger.info("Book not found with bookId : {}", bookId);
            throw new BookNotFoundException("Book Not Found with Id: "+bookId);
        }
        Book book = bookRepository.findById(bookId).get();
        bookRepository.deleteById(bookId);
        return book;
    }

    /**
     * Fetch Books within given price range
     *
     * @param minPrice
     * @param maxPrice
     * @return List of Books with given price range
     */
    @Override
    public List<Book> getBooksWithRange(double minPrice, double maxPrice) {
        if(maxPrice < minPrice) {
            throw new IncorrectRangeException("Enter Correct Range");
        }
        return bookRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
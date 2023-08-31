package com.advanz101.bookstore;

import com.advanz101.bookstore.TestHelper.BookTestData;
import com.advanz101.bookstore.entity.Book;
import com.advanz101.bookstore.exceptions.BookNotFoundException;
import com.advanz101.bookstore.exceptions.IncorrectRangeException;
import com.advanz101.bookstore.repositories.BookRepository;
import com.advanz101.bookstore.services.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void saveBookTest() {
        Book book = BookTestData.getBook();

        Mockito.when(bookRepository.save(book)).thenReturn(book);

        Assertions.assertNotNull(book);
        Assertions.assertEquals(book, bookService.saveBook(book));
    }

    @Test
    public void getBookWithExistingBookIdTest() throws BookNotFoundException {
        Book book = BookTestData.getBook();
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        Assertions.assertEquals(book.getTitle(),bookService.getBookById(book.getId()).getTitle());
    }

    @Test
    public void getBookWithNonExistingBookIdTest() throws BookNotFoundException {
        String nonExistingId = "testId";
        Mockito.when(bookRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.getBookById(nonExistingId));
   }

   @Test
   public void getAllBooksTest() {
        Mockito.when(bookRepository.findAll()).thenReturn(BookTestData.getBooks());
        Assertions.assertTrue(bookService.getBooks().size()>0);
   }

   @Test
   public void deleteBookTest() throws BookNotFoundException {
        String bookId = "testBookId";
        Mockito.when(bookRepository.existsById(bookId)).thenReturn(true);
        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(BookTestData.getBook()));

        bookService.deleteBook(bookId);
        Mockito.verify(bookRepository, Mockito.times(1)).deleteById(bookId);
   }

   @Test
   public void deleteBookWhenGivenBookIdNonExistTest() {
       String bookId = "nonExistId";
       Mockito.when(bookRepository.existsById(bookId)).thenReturn(false);

       Assertions.assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(bookId));
   }

   @Test
   public void updateBookTest() throws BookNotFoundException {
        Book book = BookTestData.getBook();
        String bookId = book.getId();

        book.setPrice(250.00);

        Mockito.when(bookRepository.existsById(bookId)).thenReturn(true);
        Mockito.when((bookRepository.save(book))).thenReturn(book);

        Assertions.assertEquals(book, bookService.updateBook(bookId, book));
   }

   @Test
   public void updateBookWhenGivenBookIdNotExistTest() {
       Book book = BookTestData.getBook();
       String bookId = book.getId();

       Mockito.when(bookRepository.existsById(bookId)).thenReturn(false);

       Assertions.assertThrows(BookNotFoundException.class, () -> bookService.updateBook(bookId, book));
   }

   @Test
   public void getBooksWithinGivenRangeTest() {
        List<Book> books = BookTestData.getBooks();
        double min = 100.00;
        double max = 200.00;

        Mockito.when(bookRepository.findByPriceBetween(min,max)).thenReturn(books);

        Assertions.assertTrue(bookService.getBooksWithRange(min, max).size()==3);
   }

   @Test
   public void getBooksWithinGivenRageWhenRangeIsIncorrectTest() {
       List<Book> books = BookTestData.getBooks();
       double min = 200.00;
       double max = 100.00;

       Assertions.assertThrows(IncorrectRangeException.class,() -> bookService.getBooksWithRange(min, max));
   }

}

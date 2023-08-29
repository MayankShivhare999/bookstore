package com.advanz101.bookstore;

import com.advanz101.bookstore.entity.Book;
import com.advanz101.bookstore.services.BookService;
import com.advanz101.bookstore.services.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

}

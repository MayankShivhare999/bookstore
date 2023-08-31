package com.advanz101.bookstore.repositories;

import com.advanz101.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByPriceBetween(double minPrice, double maxPrice);
}
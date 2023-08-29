package com.advanz101.bookstore.repositories;

import com.advanz101.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRespository extends JpaRepository<Book, String> {
}

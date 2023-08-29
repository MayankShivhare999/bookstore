package com.advanz101.bookstore.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @NonNull
    private String id;

    @NonNull
    private String title;

    private double price;

    private String author;
}

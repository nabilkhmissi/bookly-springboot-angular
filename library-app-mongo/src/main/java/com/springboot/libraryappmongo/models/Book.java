package com.springboot.libraryappmongo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class Book {
    @Id
    private String id;
    private String title;
    private String description;
    private int pages;
    private String authorId;
    private String edition;
    private int year;
    private int rating;
    private List<String> tags;
    private LocalDate addedDate;
    private int quantity;
    private double price;
    private String category;
    private String image;
    private int sale_number;


    public Book(String id,
                String title,
                String description,
                int pages,
                String authorId,
                String edition,
                int year,
                LocalDate addedDate,
                int rating,
                List<String> tags,
                int quantity,
                double price,
                String category,
                String image,
                int sale_number) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.authorId = authorId;
        this.edition = edition;
        this.year = year;
        this.rating = rating;
        this.tags = tags;
        this.addedDate = addedDate;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.image = image;
        this.sale_number = sale_number;
    }
}

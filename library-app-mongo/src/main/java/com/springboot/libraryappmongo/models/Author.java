package com.springboot.libraryappmongo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@AllArgsConstructor
@Data
@Builder
public class Author {
    @Id
    private String id;
    private String full_name;
    private String pen_name;
    private LocalDate birthday;
    private LocalDate deathDate;
    private String nationality;
}

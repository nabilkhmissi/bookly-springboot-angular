package com.springboot.libraryappmongo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Document
@Data
public class Category {
    @Id
    private String id;
    private String designation;
}

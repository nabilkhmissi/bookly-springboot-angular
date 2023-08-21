package com.springboot.libraryappmongo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
public class Confirmation {
    @Id
    private String id;
    private String token;
    private LocalDateTime tokenExpiration;

    private LocalDateTime createdDate;
    private String userId;

    public Confirmation(String userId) {
        this.userId = userId;
        token = UUID.randomUUID().toString();
        this.createdDate = LocalDateTime.now();
        tokenExpiration = LocalDateTime.now().plus(1, ChronoUnit.MINUTES);
    }
}

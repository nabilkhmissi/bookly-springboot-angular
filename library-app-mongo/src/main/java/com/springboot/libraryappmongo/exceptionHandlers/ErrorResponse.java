package com.springboot.libraryappmongo.exceptionHandlers;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class ErrorResponse {
    private LocalDate timestamp;
    private int statusCode;
    private String message;
    private List<String> errors;
}

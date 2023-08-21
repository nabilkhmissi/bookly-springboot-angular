package com.springboot.libraryappmongo.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
public class ApiResponse {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private Object data;
    private int code;
}

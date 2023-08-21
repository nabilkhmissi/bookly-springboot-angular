package com.springboot.libraryappmongo.authentication;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private LocalDate Birthday;
    private String gender;
    private String email;
    private String password;
}

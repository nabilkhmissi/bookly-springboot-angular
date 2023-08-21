package com.springboot.libraryappmongo.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}

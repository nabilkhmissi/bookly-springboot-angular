package com.springboot.libraryappmongo.authentication;

import com.springboot.libraryappmongo.dto.UserDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private UserDto user;
    private String access_token;
}

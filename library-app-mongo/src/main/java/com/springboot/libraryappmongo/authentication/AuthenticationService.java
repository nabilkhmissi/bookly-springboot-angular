package com.springboot.libraryappmongo.authentication;

import com.springboot.libraryappmongo.dto.UserDto;
import com.springboot.libraryappmongo.models.Confirmation;
import com.springboot.libraryappmongo.models.User;
import com.springboot.libraryappmongo.repo.UserRepository;
import com.springboot.libraryappmongo.security.JwtService;
import com.springboot.libraryappmongo.service.ConfirmationService;
import com.springboot.libraryappmongo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationService confirmationService;

    public String register(RegisterRequest request) {
        userService.save(request);
        return "Account created successfully, please check you email for activation link";
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = repository.findByEmail(request.getEmail());
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authentication);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .user(UserDto.fromEntity(user))
                .access_token(jwtToken)
                .build();
    }

    public String activateUserAccount(String token) {
        Confirmation confirmation = confirmationService.findByToken(token);
        if (confirmation == null) {
            return "Invalid token, please check your email for a valid activation link";
        }
        if (!confirmationService.validateConfirmation(confirmation)) {
            return "Token has been expired, contact support to generate another token";
        }
        return userService.activateUser(confirmation.getUserId());
    }
}
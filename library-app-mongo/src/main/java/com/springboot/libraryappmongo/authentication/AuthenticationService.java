package com.springboot.libraryappmongo.authentication;

import com.springboot.libraryappmongo.dto.UserDto;
import com.springboot.libraryappmongo.exception.AccountNotActiveException;
import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.models.Confirmation;
import com.springboot.libraryappmongo.models.User;
import com.springboot.libraryappmongo.repo.UserRepository;
import com.springboot.libraryappmongo.security.JwtService;
import com.springboot.libraryappmongo.service.ConfirmationService;
import com.springboot.libraryappmongo.service.EmailService;
import com.springboot.libraryappmongo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ConfirmationService confirmationService;
    private final EmailService emailService;

    public String register(RegisterRequest request) {
        UserDto user = userService.save(request);
        Confirmation confirmation = confirmationService.create(user.getId());
        String user_full_name = user.getFirstName() + " " + user.getFirstName();
        emailService.sendEmailAction("activation", user_full_name, user.getEmail(), confirmation.getToken());

        /* var jwtToken = jwtService.generateToken(UserDto.toEntity(user));*/
        /*return AuthenticationResponse.builder()
                .user(user)
                .access_token(jwtToken)
                .build();*/
        return "Account created successfully, please check your email for activation link";
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = repository.findByEmail(request.getEmail());
        if (user == null) {
            throw new EntityNotFoundException("invalid email/password");
        }
        if (!user.isEnabled()) {
            throw new AccountNotActiveException("This account is not activated yet \n" +
                    "please check your email \n" +
                    "We have sent you the activation link while signup");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user,
                user.getPassword(),
                user.getAuthorities()));

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
        LocalDateTime tokenExpiration = confirmation.getTokenExpiration();

        if (!LocalDateTime.now().isBefore(tokenExpiration)) {
            return "Token has been expired, contact support to generate another token";
        }

        return userService.activateUser(confirmation.getUserId());

    }

    public String restorePassword(String email) {
        User user = repository.findByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("invalid email/password");
        }
        Confirmation confirmation = confirmationService.create(user.getId());

        String user_full_name = user.getFirstName() + " " + user.getFirstName();
        emailService.sendEmailAction("recover", user_full_name, user.getEmail(), confirmation.getToken());
        return "Password reset link sent to your email, please check your email for for the link";
    }

    public String resetPassword(String resetToken) {
        Confirmation confirmation = confirmationService.findByToken(resetToken);
        if (confirmation == null) {
            return "Invalid token, please check your email for a valid activation link";
        }
        LocalDateTime tokenExpiration = confirmation.getTokenExpiration();

        if (!LocalDateTime.now().isBefore(tokenExpiration)) {
            return "Token has been expired, contact support to generate another token";
        }

        //todo add reset password form
        return "";
    }
}
package com.springboot.libraryappmongo.authentication;

import com.springboot.libraryappmongo.dto.ApiResponse;
import com.springboot.libraryappmongo.dto.ResetPassword;
import com.springboot.libraryappmongo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest request) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("signup successfully")
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .data(authenticationService.register(request))
                .build();
    }

    @PostMapping("/authenticate")
    public ApiResponse authenticate(@RequestBody AuthenticationRequest request) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("logged in successfully")
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .data(authenticationService.authenticate(request))
                .build();
    }

    @GetMapping("/verify")
    public String activateAccount(@RequestParam String token) {
        return authenticationService.activateUserAccount(token);
    }

    @PostMapping("/reset-password/token")
    public String requestResetToken(@RequestBody String email){
        userService.requestResetToken(email);
        return "We have sent a reset token to your email, please check you inbox";
    }

    @PostMapping("/reset-password/change")
    public String changePassword(@RequestBody ResetPassword reset){
        userService.resetPassword(reset);
        return "Password changed successfully !";
    }
}

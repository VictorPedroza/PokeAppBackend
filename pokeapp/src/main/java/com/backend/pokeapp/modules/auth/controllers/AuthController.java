package com.backend.pokeapp.modules.auth.controllers;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.pokeapp.modules.auth.entities.UserAuth;
import com.backend.pokeapp.modules.auth.interfaces.GenerateTokenRequest;
import com.backend.pokeapp.modules.auth.interfaces.LoginRequest;
import com.backend.pokeapp.modules.auth.services.AuthService;

import jakarta.validation.Valid;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequest request
    ) {

        UserAuth userAuth = authService.authenticate(request);

        String token = authService.generateToken(
                new GenerateTokenRequest(userAuth.getId())
        );

        ResponseCookie cookie = ResponseCookie
                .from("access_token", token)
                .httpOnly(true)
                .secure(false) 
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofHours(1))
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}

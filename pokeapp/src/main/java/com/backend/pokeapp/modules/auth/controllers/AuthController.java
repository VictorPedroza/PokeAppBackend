package com.backend.pokeapp.modules.auth.controllers;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.pokeapp.modules.auth.entities.UserAuth;
import com.backend.pokeapp.modules.auth.interfaces.GenerateTokenRequest;
import com.backend.pokeapp.modules.auth.interfaces.GetCredentialsRequest;
import com.backend.pokeapp.modules.auth.interfaces.LoginRequest;
import com.backend.pokeapp.modules.auth.interfaces.UserAuthResponse;
import com.backend.pokeapp.modules.auth.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequest request) {

        UserAuth userAuth = authService.authenticate(request);

        String token = authService.generateToken(
                new GenerateTokenRequest(userAuth.getId()));

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

    @GetMapping("/me")
    public ResponseEntity<UserAuthResponse> me(
            @AuthenticationPrincipal String id) {
        UserAuthResponse user = authService.getCredentials(
                new GetCredentialsRequest(id));

        return ResponseEntity.ok(user);
    }

}

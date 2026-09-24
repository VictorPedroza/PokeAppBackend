package com.backend.pokeapp.registration.presentation;

import org.springframework.web.bind.annotation.RestController;

import com.backend.pokeapp.registration.application.RegisterUserUseCase;
import com.backend.pokeapp.registration.dtos.RegisterUserRequest;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final RegisterUserUseCase registerUserUseCase;

    public RegistrationController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterUserRequest request) {
        registerUserUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

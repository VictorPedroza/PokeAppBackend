package com.backend.pokeapp.registration.application;

import java.util.UUID;

import com.backend.pokeapp.modules.auth.interfaces.CreateCredentialsRequest;
import com.backend.pokeapp.modules.auth.interfaces.GenerateTokenRequest;
import com.backend.pokeapp.modules.auth.services.AuthService;

import com.backend.pokeapp.modules.user.entities.User;
import com.backend.pokeapp.modules.user.repositories.UserRepository;
import com.backend.pokeapp.registration.dtos.RegisterUserRequest;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    private final AuthService authService;
    private final UserRepository userRepository;

    public RegisterUserUseCase(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Transactional
    public String execute(RegisterUserRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Nome de Usuário já existente");
        }

        String id = UUID.randomUUID().toString();

        CreateCredentialsRequest credentials = new CreateCredentialsRequest(
                id,
                request.email(),
                request.password());

        authService.createCredentials(credentials);

        userRepository.save(
                new User(id, request.username()));

        return authService.generateToken(new GenerateTokenRequest(id));
    }
}

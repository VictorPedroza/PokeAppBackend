package com.backend.pokeapp.modules.auth.services;

import com.backend.pokeapp.modules.auth.entities.UserAuth;
import com.backend.pokeapp.modules.auth.interfaces.CreateCredentialsRequest;
import com.backend.pokeapp.modules.auth.repositories.UserAuthRepository;

import com.backend.pokeapp.shared.utils.password.PasswordUtil;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserAuthRepository userAuthRepository;

    public AuthService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public void createCredentials(CreateCredentialsRequest request) {
        // 1. Verifica se ID de Usuário já existe
        userAuthRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("User ID already exists"));

        // TODO: 2. Validação de Email

        // 3. Hash de Senha
        String hash = PasswordUtil.hash(request.password());

        // 4. Cria instância das Novas Credenciais
        UserAuth newUser = new UserAuth(request.id(), request.email(), hash);

        userAuthRepository.save(newUser);
    }
}

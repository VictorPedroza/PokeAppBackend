package com.backend.pokeapp.modules.auth.services;

import com.backend.pokeapp.modules.auth.entities.UserAuth;
import com.backend.pokeapp.modules.auth.interfaces.CreateCredentialsRequest;
import com.backend.pokeapp.modules.auth.interfaces.GenerateTokenRequest;
import com.backend.pokeapp.modules.auth.interfaces.GetCredentialsRequest;
import com.backend.pokeapp.modules.auth.interfaces.UserAuthResponse;
import com.backend.pokeapp.modules.auth.repositories.UserAuthRepository;
import com.backend.pokeapp.shared.utils.jwt.JwtService;
import com.backend.pokeapp.shared.utils.password.PasswordUtil;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final JwtService jwtService;

    public AuthService(UserAuthRepository userAuthRepository, JwtService jwtService) {
        this.userAuthRepository = userAuthRepository;
        this.jwtService = jwtService;
    }

    public void createCredentials(CreateCredentialsRequest request) {
        // 1. Verifica se ID de Usuário já existe
        if (userAuthRepository.findById(request.id()).isPresent()) {
            throw new IllegalArgumentException("ID de Usuário já existente");
        }

        // 2. Validação de Email
        if (userAuthRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Email já existente");
        }

        // 3. Hash de Senha
        String hash = PasswordUtil.hash(request.password());

        // 4. Cria instância das Novas Credenciais
        UserAuth newUser = new UserAuth(request.id(), request.email(), hash);

        userAuthRepository.save(newUser);
    }

    public UserAuthResponse getCredentials(GetCredentialsRequest request) {
        UserAuth userAuth = userAuthRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("ID de Usuário não encontrado"));

        return new UserAuthResponse(userAuth.getId(), userAuth.getEmail());
    }

    public String generateToken(GenerateTokenRequest request) {
        return jwtService.generateToken(request.id());
    }
}

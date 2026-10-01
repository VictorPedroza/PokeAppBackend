package com.backend.pokeapp.modules.auth.interfaces;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Email não pode ser vazio")
    String email,
    @NotBlank(message = "Senha não pode ser vazio")
    String password
) {}

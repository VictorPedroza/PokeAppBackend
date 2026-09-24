package com.backend.pokeapp.registration.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @Email(message = "Email inválido")
        @NotBlank(message = "Email obrigatório")
        String email,

        @NotBlank(message = "Senha obrigatória")
        @Size(min = 8, max = 30, message = "Tamanho deve ser entre 8 e 30 caracteres")
        String password,

        @NotBlank(message = "Nome de Usuário obrigatório")
        @Size(min = 3, max = 30, message = "Tamanho deve ser entre 8 e 30 caracteres")
        String username
) {}

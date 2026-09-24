package com.backend.pokeapp.modules.auth.interfaces;

public record CreateCredentialsRequest(
    String id,
    String email,
    String password
) {}

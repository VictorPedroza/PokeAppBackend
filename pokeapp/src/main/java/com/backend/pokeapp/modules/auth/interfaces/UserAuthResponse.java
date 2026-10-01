package com.backend.pokeapp.modules.auth.interfaces;

public record UserAuthResponse(
    String id,
    String email
) { }

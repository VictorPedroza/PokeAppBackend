package com.backend.pokeapp.modules.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.pokeapp.modules.auth.entities.UserAuth;
import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, String> {

    Optional<UserAuth> findByEmail(String email);

    boolean existsByEmail(String email);
}

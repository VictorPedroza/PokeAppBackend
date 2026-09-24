package com.backend.pokeapp.modules.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.pokeapp.modules.user.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

    boolean existsByUsername(String username);
}

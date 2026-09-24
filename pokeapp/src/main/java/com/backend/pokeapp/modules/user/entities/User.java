package com.backend.pokeapp.modules.user.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "users")
public class User {
    @Id
    private String id;

    @Size(min = 3, max = 20)
    private String username;

    protected User() {}

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getters & Setters
    public String getId() { return id; }
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; } 
}

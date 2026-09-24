package com.backend.pokeapp.modules.auth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "user_auth")
public class UserAuth {
    @Id 
    private String id;

    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;

    protected UserAuth() {}

    public UserAuth(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Getters & Setters
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }
}

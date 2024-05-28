package com.pucpr.biblioteca.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class User {

    public User(String username, String password, String role, String email, String telefone) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.telefone = telefone;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;

    @Column(unique = true)
    private String email;

    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null)
            this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone != null)
            this.telefone = telefone;
    }

    // Standard getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null)
            this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null)
            this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role != null)
            this.role = role;
    }
}

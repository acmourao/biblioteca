package com.pucpr.biblioteca.dto;

public record UserDTO(
        String username,
        String role,
        String email,
        String telefone) {
}

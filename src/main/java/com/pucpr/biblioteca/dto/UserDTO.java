package com.pucpr.biblioteca.dto;

public record UserDTO(
        Long id,
        String username,
        String role,
        String email,
        String telefone) {
}

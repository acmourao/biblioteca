package com.pucpr.biblioteca.dto;

public record UserDTO(
            String username,
            String password,
            String role,
            String email,
            String telefone
) {
}

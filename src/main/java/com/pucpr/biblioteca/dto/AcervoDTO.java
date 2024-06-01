package com.pucpr.biblioteca.dto;

public record AcervoDTO(
        Long id,
        String titulo,
        String autor,
        int categoria,
        int publicacao,
        boolean active
) {
}

package com.pucpr.biblioteca.dto;

public record AcervoDTO(
    String titulo,
    String autor,
    int categoria,
    int publicacao
) {
}

package com.pucpr.biblioteca.dto;

import java.time.LocalDate;

public record LocacaoDTO(
        Long acervo,
        Long usuario,
        String titulo,
        String username,
        LocalDate emprestimo,
        LocalDate devolucao,
        boolean active
) {
}

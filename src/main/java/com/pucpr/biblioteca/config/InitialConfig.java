package com.pucpr.biblioteca.config;

import com.pucpr.biblioteca.entity.Categoria;
import com.pucpr.biblioteca.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitialConfig {
    @Bean
    public CommandLineRunner loadInitialData(CategoriaRepository categoriaRepository) {
        return (args) -> {
            categoriaRepository.save(new Categoria("LIVRO"));
            categoriaRepository.save(new Categoria("REVISTA"));
            categoriaRepository.save(new Categoria("PERIODICO"));
            categoriaRepository.save(new Categoria("PINTURA"));
            categoriaRepository.save(new Categoria("FOTOGRAFIA"));
            categoriaRepository.save(new Categoria("ESTÁTUA"));
            categoriaRepository.save(new Categoria("ABSTRATO"));
            categoriaRepository.save(new Categoria("OUTROS"));
        };
    }
}

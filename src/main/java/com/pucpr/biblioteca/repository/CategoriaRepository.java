package com.pucpr.biblioteca.repository;

import com.pucpr.biblioteca.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Iterable<Categoria> findByOrderByIdAsc();
}

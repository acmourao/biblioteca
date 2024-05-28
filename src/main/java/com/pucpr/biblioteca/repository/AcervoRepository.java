package com.pucpr.biblioteca.repository;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Categoria;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AcervoRepository extends JpaRepository<Acervo, Long> {
    public Iterable<Acervo> findByOrderByIdAsc(Limit of);
    public Iterable<Acervo> findByActiveTrueOrderByIdAsc(Limit of);
    public Iterable<Acervo> findByAutorContainingIgnoreCase(String autor);
    public Iterable<Acervo> findByTituloContainingIgnoreCase(String titulo);
    public Iterable<Acervo> findByPublicacao(int ano);
    public Iterable<Acervo> findByCategoriaAndActiveTrueOrderByTituloAsc(Categoria categoria);

    @Query("SELECT a FROM Acervo a WHERE a.id = :id and active")
    public Acervo getAcervoByIdActive(@Param("id") Long id );
}

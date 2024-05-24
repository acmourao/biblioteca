package com.pucpr.biblioteca.repository;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Categoria;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcervoRepository extends JpaRepository<Acervo, Long> {
    public Iterable<Acervo> findByActiveFalseOrderByIdAsc();
    public Iterable<Acervo> findByAutorContainingIgnoreCase(String autor);
    public Iterable<Acervo> findByTituloContainingIgnoreCase(String titulo);
    public Iterable<Acervo> findByPublicacao(int ano);
    public Iterable<Acervo> findByCategoria(Categoria categoria);
    public Iterable<Acervo> findByOrderByTituloAsc(Limit of);

//    @Query("SELECT a FROM Acervo a WHERE a.publicacao = :ano")
//    public Iterable< Acervo > getAcervoByPublicacao(@Param("ano")Year ano);
}

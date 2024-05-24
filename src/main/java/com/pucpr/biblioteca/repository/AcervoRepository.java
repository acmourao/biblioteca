package com.pucpr.biblioteca.repository;

import com.pucpr.biblioteca.entity.Acervo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcervoRepository extends JpaRepository<Acervo, Long> {
    public Iterable<Acervo> findByOrderByIdAsc();
    public Iterable<Acervo> findByAutorContainingIgnoreCase(String autor);
    public Iterable<Acervo> findByPublicacao(int ano);

//    @Query("SELECT a FROM Acervo a WHERE a.publicacao = :ano")
//    public Iterable< Acervo > getAcervoByPublicacao(@Param("ano")Year ano);
}

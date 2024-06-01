package com.pucpr.biblioteca.repository;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    public Iterable<Locacao> findByActiveTrueOrderByIdAsc();
    public Locacao findByActiveTrueAndAcervo(Acervo acervo);
    public Iterable<Locacao> findByUserAndActiveTrue(User user);
    public long countByUserAndActiveTrue(User user);

    @Query("SELECT count(l) FROM Locacao l WHERE l.user = :user and active")
    public long countByUserIdActive(@Param("user") User user );
}

package com.pucpr.biblioteca.repository;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    public Iterable<Locacao> findByActiveTrueOrderByIdAsc();
    public Locacao findByAcervo(Acervo acervo);
    public Iterable<Locacao> findByUserAndActiveTrue(User user);
    public long countByUser(User user);
}

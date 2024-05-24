package com.pucpr.biblioteca.service;

import java.time.LocalDate;
import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AcervoService acervoService;

    public Iterable<Locacao> findAllDevolucoesPendentes() {
        return locacaoRepository.findByActiveTrueOrderByIdAsc();
    }

    public Iterable<Locacao> findAllActiveByUser(Long id) {
        User user = userService.findById(id);
        return locacaoRepository.findByActiveTrueAndUserOrderByIdAsc(user);
    }

    public Iterable<Locacao> findAllActiveByAcervo(Long id) {
        Acervo acervo = acervoService.findById(id);
        return locacaoRepository.findByActiveTrueAndAcervoOrderByIdAsc(acervo);
    }

    public Locacao emprestarAcervo(LocacaoDTO locacaoDTO) {
        Locacao locacao = new Locacao();
        locacao.setUser(userService.findById( locacaoDTO.usuario() ) );
        locacao.setAcervo(acervoService.findById(locacaoDTO.acervo()));
        locacao.setEmprestimo(currentDate());
        locacao.setDevolucao(expirationDate());
        return locacaoRepository.save(locacao);
    }

    public Locacao devolverAcervo(Long id) {
        Locacao locacao = findById(id);
        //verificar se está vencido a data devoluçao
        locacao.setDevolucao(currentDate());
        return locacaoRepository.save(locacao);
    }

    public Locacao findById(Long id) {
        return locacaoRepository
                .findById(id)
                .orElse(null);
    }
    private LocalDate currentDate() {
        return LocalDate.now();
    }

    private LocalDate expirationDate() {
        return LocalDate.now().plusMonths(1);
    }
}

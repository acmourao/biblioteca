package com.pucpr.biblioteca.service;

import java.time.LocalDate;
import com.pucpr.biblioteca.auth.AuthenticationFacade;
import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.repository.LocacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private AcervoService acervoService;

    @Autowired
    private UserService userService;

    public Iterable<Locacao> findAllDevolucoesPendentes() {
        return locacaoRepository.findByOrderByIdAsc();
    }

    public Iterable<Locacao> findAllByAcervo(Long id) {
        Acervo acervo = acervoService.findById(id);
        return locacaoRepository.findByAcervoOrderByIdAsc(acervo);
    }

    public Locacao emprestarAcervo(LocacaoDTO locacaoDTO) {
        Locacao locacao = new Locacao();
        locacao.setAcervo(acervoService.findById(locacaoDTO.acervo()));
        locacao.setUser(userService.findById(locacaoDTO.usuario()));
        locacao.setEmprestimo(currentDate());
        locacao.setDevolucao(expirationDate());
        return locacaoRepository.save(locacao);
    }

    public Locacao emprestarLogado(Long id) {
        Locacao locacao = new Locacao();
        locacao.setAcervo(acervoService.findById(id));
        locacao.setUser(userService.getUserLogado());
        locacao.setEmprestimo(currentDate());
        locacao.setDevolucao(expirationDate());
        return locacaoRepository.save(locacao);
    }

    public Locacao devolverAcervo(Long id) {
        Locacao locacao = findById(id);
        //verificar se está vencido a data devoluçao
        locacao.setDevolucao(currentDate());
        locacao.setActive(false);
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

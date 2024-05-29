package com.pucpr.biblioteca.service;

import java.time.LocalDate;

import com.pucpr.biblioteca.auth.AuthenticationFacade;
import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.LocacaoRepository;
import com.pucpr.biblioteca.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocacaoService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcervoService acervoService;

    public Iterable<Locacao> findPendentesAllUsers() {
        return locacaoRepository.findByActiveTrueOrderByIdAsc();
    }

    public Iterable<Locacao> findByUserAndActive(User user) {
        return locacaoRepository.findByUserAndActiveTrue(user);
    }

    public long countByUser(User user) {
        return locacaoRepository.countByUser(user);
    }

    public Iterable<Locacao> findByUserId(Long id) {
        return findByUserAndActive(findUserById(id));
    }

    public User findUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ServiceException("Usuário não encontrado pelo Id!"));
    }

    public Locacao locarAcervo(Long idAcervo) {
        Acervo acervo = isDisponivel(idAcervo);
        User user = authenticationFacade.getUser();
        checarLimiteLocacao(user);
        return setLocacao(acervo, user);
    }

    public Locacao emprestarAcervo(LocacaoDTO locacaoDTO) {
        Acervo acervo = isDisponivel(locacaoDTO.acervo());
        User user = findUserById(locacaoDTO.usuario());
        checarLimiteLocacao(user);
        return setLocacao(acervo, user);
    }

    private Locacao setLocacao(Acervo acervo, User user) {
        Locacao locacao = new Locacao();
        locacao.setAcervo( acervoService.setStatus(acervo,false ) );
        locacao.setUser(user);
        locacao.setEmprestimo(currentDate());
        locacao.setDevolucao(expirationDate());
        return locacaoRepository.save(locacao);
    }

    private Acervo isDisponivel(Long idAcervo) {
        return acervoService.isDisponivel(idAcervo);
    }

    private void checarLimiteLocacao(User user) {
        if (countByUser(user) > 2) {
            throw new RuntimeException("Limite de locações por usuário atingido!");
        }
    }

    public Locacao devolverAcervo(Long idAcervo) {
        Acervo acervo = acervoService.findById(idAcervo);
        Locacao locacao = baixarLocacao(findLocacaoByAcervo(acervo));
        return locacaoRepository.save(locacao);
    }

    public Locacao baixarLocacao(Locacao locacao) {
        locacao.setAcervo( acervoService.setStatus(locacao.getAcervo(), true) );
        locacao.setDevolucao(currentDate());
        locacao.setActive(false);
        return locacaoRepository.save(locacao);
    }

    public Locacao findByAcervoId(Long id) {
        Acervo acervo = acervoService.findById(id);
        return findLocacaoByAcervo(acervo);
    }

    public Locacao findLocacaoByAcervo(Acervo acervo) {
        Locacao locacao = locacaoRepository.findByActiveTrueAndAcervo(acervo);
        if (locacao == null) {
            throw new RuntimeException("Acervo não está locado!");
        }
        return locacao;
    }

    public Locacao findById(Long id) {
        return locacaoRepository
                .findById(id)
                .orElseThrow(() -> new ServiceException("Locação não encontrada!"));
    }

    private LocalDate currentDate() {
        return LocalDate.now();
    }

    private LocalDate expirationDate() {
        return LocalDate.now().plusMonths(1);
    }

}

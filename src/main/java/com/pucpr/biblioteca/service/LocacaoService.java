package com.pucpr.biblioteca.service;

import java.time.LocalDate;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.entity.User;
import com.pucpr.biblioteca.repository.LocacaoRepository;
import org.hibernate.service.spi.ServiceException;
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
        return findByUserAndActive(userService.findById(id));
    }

    public Locacao emprestarAcervo(Long idAcervo) {
        Acervo acervo = acervoService.isDisponivel(idAcervo);
        if (acervo == null) {
            throw new RuntimeException("Acervo não disponível!");
        }

        User user = userService.getUserLogado();
        if (countByUser(user) > 2) {
            throw new RuntimeException("Limite de locações por usuário atingido!");
        }

        Locacao locacao = new Locacao();
        locacao.setAcervo( acervoService.setStatus(acervo,false ) );
        locacao.setUser(user);
        locacao.setEmprestimo(currentDate());
        locacao.setDevolucao(expirationDate());
        return locacaoRepository.save(locacao);
    }

    public Locacao devolverAcervo(Long idAcervo) {
        Locacao locacao = findByAcervoId(idAcervo);
        locacao.setAcervo( acervoService.setStatusById( idAcervo, true) );
        //Falta verificar se está vencido na data devoluçao
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

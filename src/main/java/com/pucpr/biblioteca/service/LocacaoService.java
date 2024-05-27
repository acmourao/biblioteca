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
    private AcervoService acervoService;

    @Autowired
    private UserService userService;

    public Iterable<Locacao> findPendentesAllUsers() {
        return locacaoRepository.findByActiveTrueOrderByIdAsc();
    }

    public Locacao findByAcervo(Long id) {
        Acervo acervo = acervoService.findById(id);
        return locacaoRepository.findByAcervo(acervo);
    }

    public Iterable<Locacao> findByUserAndActive(User user) {
        return locacaoRepository.findByUserAndActiveTrue(user);
    }

    public long countByUser(User user) {
        return locacaoRepository.countByUser(user);
    }

    public Iterable<Locacao> findByUserId(Long id) {
        User user = userService.findById(id);
        return findByUserAndActive(user);
    }

    public Locacao emprestarAcervo(LocacaoDTO locacaoDTO) {
        Acervo acervo = acervoService.isDisponivel(locacaoDTO.acervo());
        if (acervo == null) {
            throw new RuntimeException("Acervo não disponível!");
        }

        User user = userService.findById( locacaoDTO.usuario() );
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

    public Locacao emprestarAcervoUserLogado(Long idAcervo) {
        LocacaoDTO locacaoDTO = new LocacaoDTO(idAcervo, userService.getUserLogado().getId());
        return emprestarAcervo(locacaoDTO);
    }

    public Locacao devolverAcervo(Long id) {
        Locacao locacao = findById(id);
        Acervo acervo = acervoService.setStatusById( locacao.getAcervo().getId(), true);
        locacao.setAcervo(acervo);
        //Falta verificar se está vencido na data devoluçao
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

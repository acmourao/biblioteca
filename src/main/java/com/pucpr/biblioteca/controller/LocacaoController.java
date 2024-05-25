package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.LocacaoDTO;
import com.pucpr.biblioteca.entity.Locacao;
import com.pucpr.biblioteca.service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locacao")
public class LocacaoController {

    @Autowired
    private LocacaoService locacaoService;

    @GetMapping
    public ResponseEntity<Iterable<Locacao>> findPendentesAllUsers() {
        Iterable<Locacao> locacoes = locacaoService.findPendentesAllUsers();
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Locacao> findById(@PathVariable Long id) {
        return ResponseEntity.ok(locacaoService.findById(id));
    }

    @GetMapping(value = "/acervo/{id}")
    public ResponseEntity<Iterable<Locacao>> findAllbyAcervo(@PathVariable Long id) {
        Iterable<Locacao> locacoes = locacaoService.findAllByAcervo(id);
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Iterable<Locacao>> findAllByUser(@PathVariable Long id) {
        Iterable<Locacao> locacoes = locacaoService.findAllByUser(id);
        return ResponseEntity.ok(locacoes);
    }

    @PostMapping("/emprestarAcervoUserLogado/{id}")
    public Locacao emprestarAcervoUserLogado(@PathVariable Long id) {
        return locacaoService.emprestarAcervoUserLogado(id);
    }

}

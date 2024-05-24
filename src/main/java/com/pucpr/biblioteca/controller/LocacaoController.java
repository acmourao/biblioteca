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
    public ResponseEntity<Iterable<Locacao>> findAllDevolucoesPendentes() {
        Iterable<Locacao> locacoes = locacaoService.findAllDevolucoesPendentes();
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Locacao> findById(@PathVariable Long id) {
        return ResponseEntity.ok(locacaoService.findById(id));
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Iterable<Locacao>> findPendenciasByUserId(@PathVariable Long id) {
        Iterable<Locacao> locacoes = locacaoService.findAllActiveByUser(id);
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping(value = "/acervo/{id}")
    public ResponseEntity<Iterable<Locacao>> findAcervoByUserId(@PathVariable Long id) {
        Iterable<Locacao> locacoes = locacaoService.findAllActiveByAcervo(id);
        return ResponseEntity.ok(locacoes);
    }

    @PostMapping("/emprestar")
    public Locacao emprestarAcervo(@RequestBody LocacaoDTO locacaoDTO) {
        return locacaoService.emprestarAcervo(locacaoDTO);
    }

    @PostMapping("/devolver/{id}")
    public Locacao devolverAcervo(@PathVariable Long id) {
        return locacaoService.devolverAcervo(id);
    }

}

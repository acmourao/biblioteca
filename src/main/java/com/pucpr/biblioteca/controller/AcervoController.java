package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.service.AcervoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acervo")
public class AcervoController {

    @Autowired
    private AcervoService acervoService;

    @GetMapping
    public ResponseEntity< Iterable<Acervo> > findAll(){
        Iterable<Acervo> acervos = acervoService.findAll();
        return ResponseEntity.ok(acervos);
    }

    @GetMapping(value = "/publicacao/{ano}")
    public ResponseEntity< Iterable<Acervo> > findByPublicacao(@PathVariable int ano){
        Iterable<Acervo> acervos = acervoService.findByPublicacao(ano);
        return ResponseEntity.ok(acervos);
    }

    @GetMapping(value = "/autor/{autor}")
    public ResponseEntity< Iterable<Acervo> > findAllByAutor(@PathVariable String autor){
        Iterable<Acervo> acervos = acervoService.findAllByAutor(autor);
        return ResponseEntity.ok(acervos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Acervo> findById(@PathVariable Long id) {
        return ResponseEntity.ok( acervoService.findById(id) );
    }

}

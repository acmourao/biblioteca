package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.AcervoDTO;
import org.springframework.http.HttpStatus;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.service.AcervoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acervo")
public class AcervoController {

    @Autowired
    private AcervoService acervoService;

    @GetMapping
    public ResponseEntity< Iterable<Acervo> > findAll(){
        Iterable<Acervo> acervo = acervoService.findAll(10);
        return ResponseEntity.ok(acervo);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity< Iterable<Acervo> > findDisponiveis(){
        return ResponseEntity.ok(acervoService.findAllDisponiveis(10));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acervo> findById(@PathVariable Long id) {
        return ResponseEntity.ok( acervoService.findById(id) );
    }

    @GetMapping("/publicacao/{ano}")
    public ResponseEntity< Iterable<Acervo> > findByPublicacao(@PathVariable int ano){
        return ResponseEntity.ok(acervoService.findByPublicacao(ano));
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity< Iterable<Acervo> > findAllByAutor(@PathVariable String autor){
        return ResponseEntity.ok(acervoService.findByAutor(autor));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity< Iterable<Acervo> > findByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(acervoService.findByTitulo(titulo));
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity< Iterable<Acervo> > findByCategoria(@PathVariable int idCategoria){
        return ResponseEntity.ok(acervoService.findByCategoria(idCategoria));
    }

}

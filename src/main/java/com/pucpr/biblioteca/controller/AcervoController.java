package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.service.AcervoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acervo")
public class AcervoController {

    @Autowired
    private AcervoService acervoService;

    @GetMapping
    public ResponseEntity< Iterable<Acervo> > findAllLimit(){
        Iterable<Acervo> acervos = acervoService.findByOrderByTituloAsc();
        return ResponseEntity.ok(acervos);
    }

    @GetMapping(value = "/emprestados")
    public ResponseEntity< Iterable<Acervo> > findAllActives(){
        Iterable<Acervo> acervos = acervoService.findAllEmprestados();
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

    @GetMapping(value = "/titulo/{titulo}")
    public ResponseEntity< Iterable<Acervo> > findAllByTitulo(@PathVariable String titulo){
        Iterable<Acervo> acervos = acervoService.findByTitulo(titulo);
        return ResponseEntity.ok(acervos);
    }

    @GetMapping(value = "/categoria/{id}")
    public ResponseEntity< Iterable<Acervo> > findAllByCategoria(@PathVariable int idCategoria){
        Iterable<Acervo> acervos = acervoService.findByCategoria(idCategoria);
        return ResponseEntity.ok(acervos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Acervo> findById(@PathVariable Long id) {
        return ResponseEntity.ok( acervoService.findById(id) );
    }

    @PostMapping(value = "/liberaOuBloqueia/{id}", consumes = "application/json")
    public ResponseEntity<Acervo> liberaBloqueiaById(@PathVariable Long id, @RequestBody int status) {
        return ResponseEntity.ok( acervoService.liberaBloqueiaById(id, status) );
    }

}

package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.AcervoDTO;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Categoria;
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

    @PostMapping("/add")
    public ResponseEntity<Acervo> addAcervo(@RequestBody AcervoDTO acervoDTO) {
        return ResponseEntity.ok( acervoService.addAcervo(acervoDTO) );
    }

    @GetMapping("/disponiveis")
    public ResponseEntity< Iterable<Acervo> > findDisponiveis(){
        Iterable<Acervo> acervos = acervoService.findAllDisponiveis(10);
        return ResponseEntity.ok(acervos);
    }

    @GetMapping("/indisponiveis")
    public ResponseEntity< Iterable<Acervo> > findAllOut(){
        Iterable<Acervo> acervos = acervoService.findIndisponiveis();
        return ResponseEntity.ok(acervos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acervo> findById(@PathVariable Long id) {
        return ResponseEntity.ok( acervoService.findById(id) );
    }

    @GetMapping("/publicacao/{ano}")
    public ResponseEntity< Iterable<Acervo> > findByPublicacao(@PathVariable int ano){
        Iterable<Acervo> acervos = acervoService.findByPublicacao(ano);
        return ResponseEntity.ok(acervos);
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity< Iterable<Acervo> > findAllByAutor(@PathVariable String autor){
        Iterable<Acervo> acervos = acervoService.findByAutor(autor);
        return ResponseEntity.ok(acervos);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity< Iterable<Acervo> > findByTitulo(@PathVariable String titulo){
        Iterable<Acervo> acervos = acervoService.findByTitulo(titulo);
        return ResponseEntity.ok(acervos);
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity< Iterable<Acervo> > findByCategoria(@PathVariable int idCategoria){
        Iterable<Acervo> acervos = acervoService.findByCategoria(idCategoria);
        return ResponseEntity.ok(acervos);
    }

}

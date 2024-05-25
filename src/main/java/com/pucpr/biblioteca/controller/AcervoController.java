package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.AcervoDTO;
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

    @PostMapping
    public Acervo addAcervo(@RequestBody AcervoDTO acervoDTO) {
        return acervoService.addAcervo(acervoDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Acervo> findById(@PathVariable Long id) {
        return ResponseEntity.ok( acervoService.findById(id) );
    }

    @GetMapping(value = "/disponiveis")
    public ResponseEntity< Iterable<Acervo> > findAllActivesLimit(){
        Iterable<Acervo> acervos = acervoService.findByOrderByTituloAsc();
        return ResponseEntity.ok(acervos);
    }

    @GetMapping(value = "/indisponiveis")
    public ResponseEntity< Iterable<Acervo> > findAllOut(){
        Iterable<Acervo> acervos = acervoService.findAllIndisponiveis();
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
    public ResponseEntity< Iterable<Acervo> > findAllByCategoria(@PathVariable int id){
        Iterable<Acervo> acervos = acervoService.findByCategoria(id);
        return ResponseEntity.ok(acervos);
    }

    @PostMapping(value = "/liberarBloquear/{id}", consumes = "application/json")
    public ResponseEntity<Acervo> liberaBloqueiaById(@PathVariable Long id, @RequestBody boolean status) {
        return ResponseEntity.ok( acervoService.setStatusById(id, status) );
    }


}

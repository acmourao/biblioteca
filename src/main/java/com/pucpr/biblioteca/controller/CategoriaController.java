package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.entity.Categoria;
import com.pucpr.biblioteca.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity< Iterable<Categoria> > consultaTodasCategorias(){
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable int id) {
        return ResponseEntity.ok( categoriaService.findById(id) );
    }
}

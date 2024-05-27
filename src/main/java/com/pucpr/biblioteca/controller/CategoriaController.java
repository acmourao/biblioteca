package com.pucpr.biblioteca.controller;

import com.pucpr.biblioteca.dto.CategoriaDTO;
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
        Iterable<Categoria> categoria = categoriaService.findAll();
        return ResponseEntity.ok(categoria);
    }

    @PostMapping("/add")
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok( categoriaService.addCategoria(categoria) );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable int id) {
        return ResponseEntity.ok( categoriaService.findById(id) );
    }
}

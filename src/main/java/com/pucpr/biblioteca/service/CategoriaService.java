package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.dto.CategoriaDTO;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Categoria;
import com.pucpr.biblioteca.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Iterable<CategoriaDTO> findAll() {
        //repository.findAll(Sort.by(Sort.Direction.DESC, "colName").ignoreCase());
        //List<Passenger> passengers = repository.findAll(Sort.by(Sort.Direction.ASC, "seatNumber"));
        return categoriaRepository.findByOrderByIdAsc();
    }

    public Categoria findById(int id) {
        return categoriaRepository
                .findById(id)
                .orElse(null);
    }

    public Iterable<Acervo> acervos(int id) {
        return findById(id).getAcervo();
    }
}

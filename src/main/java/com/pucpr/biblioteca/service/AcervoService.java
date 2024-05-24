package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.repository.AcervoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcervoService {

    @Autowired
    private AcervoRepository acervoRepository;

    public Iterable<Acervo> findAll() {
        return acervoRepository.findByOrderByIdAsc();
    }

    public Iterable<Acervo> findByPublicacao(int ano) {
        return acervoRepository.findByPublicacao(ano);
    }

    public Iterable<Acervo> findAllByAutor(String autor) {
        return acervoRepository.findByAutorContainingIgnoreCase(autor);
    }

    public Acervo findById(Long id) {
        return acervoRepository
                .findById(id)
                .orElse(null);
    }

}

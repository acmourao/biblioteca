package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.entity.Categoria;
import com.pucpr.biblioteca.repository.CategoriaRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria manterCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public String deletar(int id) {
        categoriaRepository.delete(findById(id));
        return "redirect:/";
    }

    public Iterable<Categoria> findAll() {
        //repository.findAll(Sort.by(Sort.Direction.DESC, "colName").ignoreCase());
        //List<Passenger> passengers = repository.findAll(Sort.by(Sort.Direction.ASC, "seatNumber"));
        return categoriaRepository.findByOrderByIdDesc();
    }

    public Categoria findById(int id) {
        return categoriaRepository
                .findById(id)
                .orElseThrow(() -> new ServiceException("Categoria não encontrada!"));
    }

}

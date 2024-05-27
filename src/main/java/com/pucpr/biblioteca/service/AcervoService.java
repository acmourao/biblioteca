package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.dto.AcervoDTO;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Categoria;
import com.pucpr.biblioteca.repository.AcervoRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

@Service
public class AcervoService {

    @Autowired
    private AcervoRepository acervoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Acervo addAcervo(AcervoDTO acervoDTO) {
        Acervo acervo = new Acervo(
                acervoDTO.titulo(),
                acervoDTO.autor(),
                acervoDTO.publicacao(),
                categoriaService.findById(acervoDTO.categoria()));
        return acervoRepository.save(acervo);
    }

    public Iterable<Acervo> findAll(int limit) {
        return acervoRepository.findByOrderByIdAsc(Limit.of(limit));
    }

    public Acervo isDisponivel(Long id) {
        return acervoRepository.getAcervoByIdActive(id);
    }

    public Iterable<Acervo> findAllDisponiveis(int limit) {
        return acervoRepository.findByActiveTrueOrderByIdAsc(Limit.of(limit));
    }

    public Iterable<Acervo> findIndisponiveis() {
        return acervoRepository.findByActiveFalseOrderByIdAsc(Limit.of(10));
    }

    public Iterable<Acervo> findByPublicacao(int ano) {
        return acervoRepository.findByPublicacao(ano);
    }

    public Iterable<Acervo> findByCategoria(int idCategoria) {
        Categoria categoria = categoriaService.findById(idCategoria);
        return acervoRepository.findByCategoria(categoria);
    }

    public Iterable<Acervo> findByAutor(String autor) {
        return acervoRepository.findByAutorContainingIgnoreCase(autor);
    }

    public Iterable<Acervo> findByTitulo(String titulo) {
        return acervoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Acervo findById(Long id) {
        return acervoRepository
                .findById(id)
                .orElse(null);
    }


    public Acervo setStatus(Acervo acervo, boolean status) {
        acervo.setActive(status);
        return acervoRepository.save(acervo);
    }

    public Acervo setStatusById(Long id, boolean status) {
        Acervo acervo = findById(id);
        if (acervo == null) {
            throw new ServiceException("Título Id não encontrado!");
        }
        acervo.setActive(status);
        acervoRepository.save(acervo);
        return acervo;
    }


}

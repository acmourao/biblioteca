package com.pucpr.biblioteca.service;

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

    public Iterable<Acervo> findByOrderByTituloAsc() {
        return acervoRepository.findByOrderByTituloAsc(Limit.of(5));
    }

    public Iterable<Acervo> findAllEmprestados() {
        return acervoRepository.findByActiveFalseOrderByIdAsc();
    }

    public Iterable<Acervo> findByPublicacao(int ano) {
        return acervoRepository.findByPublicacao(ano);
    }

    public Iterable<Acervo> findByCategoria(int id) throws ServiceException {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) {
            throw new ServiceException("Categoria Id não encontrado!");
        }
        return acervoRepository.findByCategoria(categoria);
    }

    public Iterable<Acervo> findAllByAutor(String autor) {
        return acervoRepository.findByAutorContainingIgnoreCase(autor);
    }

    public Iterable<Acervo> findByTitulo(String autor) {
        return acervoRepository.findByTituloContainingIgnoreCase(autor);
    }

    public Acervo findById(Long id) {
        return acervoRepository
                .findById(id)
                .orElse(null);
    }

    public Acervo liberarBloquearById(Long id, int status) throws ServiceException {
        Acervo acervo = findById(id);
        if (acervo == null) {
            throw new ServiceException("Título Id não encontrado!");
        }
        acervo.setActive(status==1);
        acervoRepository.save(acervo);
        return acervo;
    }


}

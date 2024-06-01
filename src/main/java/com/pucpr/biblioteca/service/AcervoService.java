package com.pucpr.biblioteca.service;

import com.pucpr.biblioteca.dto.AcervoDTO;
import com.pucpr.biblioteca.entity.Acervo;
import com.pucpr.biblioteca.entity.Categoria;
import com.pucpr.biblioteca.repository.AcervoRepository;
import com.pucpr.biblioteca.repository.LocacaoRepository;
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

    @Autowired
    private LocacaoRepository locacaoRepository;

    public Acervo manterAcervo(AcervoDTO acervoDTO) {
        Acervo acervo = (acervoDTO.id() == null) ? new Acervo() : findById(acervoDTO.id());
        acervo.setTitulo(acervoDTO.titulo());
        acervo.setAutor(acervoDTO.autor());
        acervo.setPublicacao(acervoDTO.publicacao());
        acervo.setCategoria(categoriaService.findById(acervoDTO.categoria()));
        acervo.setActive(acervoDTO.active());
        acervoRepository.save(acervo);
        return acervo;
    }

    public String deletar(Long id) {
        acervoRepository.delete(findById(id));
        return "redirect:/";
    }

    public Iterable<Acervo> findAll(int limit) {
        return acervoRepository.findByOrderByIdAsc(Limit.of(limit));
    }

    public Acervo isDisponivel(Long id) {
        Acervo acervo = acervoRepository.getAcervoByIdActive(id);
        if (acervo == null) {
            throw new RuntimeException("Acervo não disponível ou inexistente!");
        }
        return acervo;
    }

    public Iterable<Acervo> findAllDisponiveis(int limit) {
        return acervoRepository.findByActiveTrueOrderByIdAsc(Limit.of(limit));
    }

    public Iterable<Acervo> findByPublicacao(int ano) {
        return acervoRepository.findByPublicacao(ano);
    }

    public Iterable<Acervo> findByCategoria(int idCategoria) {
        Categoria categoria = categoriaService.findById(idCategoria);
        return acervoRepository.findByCategoriaAndActiveTrueOrderByTituloAsc(categoria);
    }

    public Iterable<Acervo> findByAutor(String autor) {
        return acervoRepository.findByAutorContainingIgnoreCase(autor);
    }

    public Iterable<Acervo> findByTitulo(String titulo) {
        return acervoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Acervo setStatus(Acervo acervo, boolean status) {
        acervo.setActive(status);
        return acervoRepository.save(acervo);
    }

    public Acervo findById(Long id) {
        return acervoRepository
                .findById(id)
                .orElseThrow(() -> new ServiceException("Título não encontrado!"));
    }
}

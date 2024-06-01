package com.pucpr.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(indexes = @Index(columnList = "autor"))
public class Acervo {

    public Acervo(String titulo, String autor, int publicacao, Categoria categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.publicacao = publicacao;
        this.categoria = categoria;
        this.active = true;
    }

    public Acervo() {
        this.active = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String autor;

    private int publicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})

    private Categoria categoria;

    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null)
            this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null)
            this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor != null)
            this.autor = autor;
    }

    public int getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(int publicacao) {
        if (publicacao != 0)
            this.publicacao = publicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria != null)
            this.categoria = categoria;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        if (active != null)
            this.active = active;
    }
}

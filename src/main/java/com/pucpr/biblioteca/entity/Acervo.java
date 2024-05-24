package com.pucpr.biblioteca.entity;

import jakarta.persistence.*;

import java.time.Year;

@Entity
@Table(indexes = @Index(columnList = "autor,publicacao"))
public class Acervo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String autor;

    private int publicacao;

    @ManyToOne
    private Categoria categoria;

    public Acervo() {
    }

    public Acervo(String titulo, String autor, Categoria categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(int publicacao) {
        this.publicacao = publicacao;
    }
}

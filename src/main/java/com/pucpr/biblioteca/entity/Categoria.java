package com.pucpr.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
//@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String tipo;

    public Categoria() {
    }

    public Categoria(String tipo) {
        this.tipo = tipo;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<Acervo> acervo;

    @JsonManagedReference(value = "categoria")
    public List<Acervo> getAcervo() {
        return acervo;
    }

    public void setAcervo(List<Acervo> acervo) {
        this.acervo = acervo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

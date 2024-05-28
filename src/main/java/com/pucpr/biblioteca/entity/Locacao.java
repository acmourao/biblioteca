package com.pucpr.biblioteca.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
//@Table(indexes = @Index(columnList = "acervo_id, user_id"))
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"acervo_id", "user_id"})})

public class Locacao {

    public Locacao() {
        this.active = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Acervo acervo;

    @ManyToOne
    private User user;

    private LocalDate devolucao;
    private LocalDate emprestimo;

    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Acervo getAcervo() {
        return acervo;
    }

    public void setAcervo(Acervo acervo) {
        this.acervo = acervo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(LocalDate emprestimo) {
        this.emprestimo = emprestimo;
    }

    public LocalDate getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(LocalDate devolucao) {
        this.devolucao = devolucao;
    }

}

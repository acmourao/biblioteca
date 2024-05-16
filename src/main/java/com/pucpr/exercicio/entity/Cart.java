package com.pucpr.exercicio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user;
    private Date date;
    
    private boolean active;

    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isActive(){
        return active;
    }

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();

    public Cart() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonManagedReference(value = "cart")
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}

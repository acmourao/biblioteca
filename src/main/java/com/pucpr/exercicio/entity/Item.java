package com.pucpr.exercicio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer uid;
    private String name;
    private Integer quantity;
    private double value;
    private String description;

//    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

//    @ManyToOne(fetch = FetchType.LAZY)
    private Inventory inventory;

    public Item() {
        
    }
    public Item(String name, Integer quantity, double value, String description) {
        this.name = name;
        this.quantity = quantity;
        this.value = value;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double valor) {
        this.value = valor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUid(Integer id) {
        this.uid = id;
    }

    public Integer getUid() {
        return uid;
    }

//    @JsonBackReference(value="cart")
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

//    @JsonBackReference(value="inventory")
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}

package com.example.topshopapi.entitys;

import javax.persistence.*;

@Entity
@Table(name = "product_inventory")
public class ProductInventory {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    // Constructors
    public ProductInventory() {}

    public ProductInventory(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

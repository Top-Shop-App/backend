package com.example.topshopapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/*
Shopping session is the users current cart. Once user places order,
the shopping session (cart) will then be cleared.
 */

@Entity
@Table(name = "shopping_session")
public class ShoppingSession {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "total")
    private BigDecimal cartTotal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    // Constructors
    public ShoppingSession() {}

    public ShoppingSession(long id, BigDecimal cartTotal, User user) {
        this.id = id;
        this.cartTotal = cartTotal;
        this.user = user;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

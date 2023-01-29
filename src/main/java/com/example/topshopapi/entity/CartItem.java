package com.example.topshopapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity", nullable = false)
    private int cartQuantity;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false, referencedColumnName = "id")
    private ShoppingSession shoppingSession;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    private Product product;

    // Constructors
    public CartItem() {}

    public CartItem(long id, int cartQuantity, ShoppingSession shoppingSession, Product product) {
        this.id = id;
        this.cartQuantity = cartQuantity;
        this.shoppingSession = shoppingSession;
        this.product = product;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public ShoppingSession getShoppingSession() {
        return shoppingSession;
    }

    public void setShoppingSession(ShoppingSession shoppingSession) {
        this.shoppingSession = shoppingSession;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

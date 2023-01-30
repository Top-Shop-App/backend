package com.example.topshopapi.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_product_details")
public class OrderProductDetails {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_details_id", nullable = false)
    private OrderDetails orderDetails;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    // Constructors
    public OrderProductDetails() {}

    public OrderProductDetails(long id, OrderDetails orderDetails, Product product, int quantity) {
        this.id = id;
        this.orderDetails = orderDetails;
        this.product = product;
        this.quantity = quantity;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOrderTotal() {
        return quantity;
    }

    public void setOrderTotal(int quantity) {
        this.quantity = quantity;
    }
}

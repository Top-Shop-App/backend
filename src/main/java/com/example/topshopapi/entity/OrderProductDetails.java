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

    @Column(name = "order_total", nullable = false)
    private BigDecimal orderTotal;

    // Constructors
    public OrderProductDetails() {}

    public OrderProductDetails(long id, OrderDetails orderDetails, Product product, BigDecimal orderTotal) {
        this.id = id;
        this.orderDetails = orderDetails;
        this.product = product;
        this.orderTotal = orderTotal;
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

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }
}

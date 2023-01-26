package com.example.topshopapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id")
    private OrderDetail orderDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    private Product product;

    // Constructors
    public OrderItem() {}

    public OrderItem(long id, int quantity, OrderDetail orderDetail, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.orderDetail = orderDetail;
        this.product = product;
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

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

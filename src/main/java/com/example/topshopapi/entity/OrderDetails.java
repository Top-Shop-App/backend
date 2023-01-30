package com.example.topshopapi.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_details")
public class OrderDetails {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Many orders can be associated with one user.
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many orders can be associated with one address.
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    // Many orders can be associated with one payment.
    @ManyToOne(optional = false)
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @OneToMany(mappedBy = "id", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderProductDetails> orderProductDetails = new ArrayList<>();

    // Constructor
    public OrderDetails() {}

    public OrderDetails(long id, User user, Address address, Payment payment, List<OrderProductDetails> orderProductDetails) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.payment = payment;
        this.orderProductDetails = orderProductDetails;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getUserAddress() {
        return address;
    }

    public void setUserAddress(Address address) {
        this.address = address;
    }

    public Payment getUserPayment() {
        return payment;
    }

    public void setUserPayment(Payment payment) {
        this.payment = payment;
    }

    public List<OrderProductDetails> getOrderProductDetails() {
        return orderProductDetails;
    }

    public void setOrderProductDetails(List<OrderProductDetails> orderProductDetails) {
        this.orderProductDetails = orderProductDetails;
    }
}

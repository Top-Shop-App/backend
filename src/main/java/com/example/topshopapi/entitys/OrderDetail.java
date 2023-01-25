package com.example.topshopapi.entitys;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", nullable = false, referencedColumnName = "id")
    private PaymentDetail paymentDetail;

    // Constructors
    public OrderDetail() {}

    public OrderDetail(long id, User user, PaymentDetail paymentDetail) {
        this.id = id;
        this.user = user;
        this.paymentDetail = paymentDetail;
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

    public PaymentDetail getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(PaymentDetail paymentDetail) {
        this.paymentDetail = paymentDetail;
    }
}

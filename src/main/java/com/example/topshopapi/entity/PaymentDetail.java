package com.example.topshopapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payment_details")
public class PaymentDetail {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id", nullable = false, unique = true)
    private int orderId;

    @Column(name = "payment_amount", nullable = false)
    private BigDecimal paymentAmount;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    // Constructors
    public PaymentDetail() {}

    public PaymentDetail(long id, int orderId, BigDecimal paymentAmount, String orderStatus) {
        this.id = id;
        this.orderId = orderId;
        this.paymentAmount = paymentAmount;
        this.orderStatus = orderStatus;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}

package com.example.topshopapi.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_payment")
public class UserPayment {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "provider")
    private String provider;

    @Column(name = "account_no")
    private int accountNo;

    @Column(name = "expir_date")
    private Date expirDate;

    // Many payments can be saved to one user.
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "created_at"})
    private User user;

    // Constructors
    public UserPayment() {}

    public UserPayment(long id, String paymentType, String provider, int accountNo, Date expirDate, User user) {
        this.id = id;
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNo = accountNo;
        this.expirDate = expirDate;
        this.user = user;
    }

    public UserPayment(String paymentType, String provider, int accountNo, Date expirDate, User user) {
        this.paymentType = paymentType;
        this.provider = provider;
        this.accountNo = accountNo;
        this.expirDate = expirDate;
        this.user = user;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public Date getExpirDate() {
        return expirDate;
    }

    public void setExpirDate(Date expirDate) {
        this.expirDate = expirDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

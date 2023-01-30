package com.example.topshopapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "card_no", nullable = false)
    private int cardNo;

    @Column(name = "cardholder_name", nullable = false)
    private String cardHolderName;

    @Column(name = "expir_date", nullable = false)
    private String expirDate;

    @Column(name = "cvc_no", nullable = false, length = 3)
    private int cvcNo;

    // Many payments can be saved to one user. (Optional)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password"})
    private User user;

    // Constructors
    public Payment() {}

    public Payment(long id, String paymentType, String provider, String cardHolderName, int cardNo, String expirDate, int cvcNo, User user) {
        this.id = id;
        this.paymentType = paymentType;
        this.provider = provider;
        this.cardHolderName = cardHolderName;
        this.cardNo = cardNo;
        this.expirDate = expirDate;
        this.cvcNo = cvcNo;
        this.user = user;
    }

    public Payment(String paymentType, String provider, int cardNo, String cardHolderName, String expirDate, int cvcNo, User user) {
        this.paymentType = paymentType;
        this.provider = provider;
        this.cardHolderName = cardHolderName;
        this.cardNo = cardNo;
        this.expirDate = expirDate;
        this.cvcNo = cvcNo;
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

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpirDate() {
        return expirDate;
    }

    public void setExpirDate(String expirDate) {
        this.expirDate = expirDate;
    }

    public int getCvcNo() {
        return cvcNo;
    }

    public void setCvcNo(int cvcNo) {
        this.cvcNo = cvcNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

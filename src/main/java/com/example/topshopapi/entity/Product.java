package com.example.topshopapi.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_desc", length = 500)
    private String productDesc;

    @Column(name = "product_sku", nullable = false, length = 50)
    private String productSku;

    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    private ProductCategory productCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", nullable = false, referencedColumnName = "id")
    private ProductInventory productInventory;

    // Constructors
    public Product() {}

    public Product(long id, String productName, String productDesc, String productSku, BigDecimal productPrice, ProductCategory productCategory, ProductInventory productInventory) {
        this.id = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productSku = productSku;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productInventory = productInventory;
    }

    public Product(String productName, String productDesc, String productSku, BigDecimal productPrice, ProductCategory productCategory, ProductInventory productInventory) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.productSku = productSku;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productInventory = productInventory;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductInventory getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(ProductInventory productInventory) {
        this.productInventory = productInventory;
    }
}

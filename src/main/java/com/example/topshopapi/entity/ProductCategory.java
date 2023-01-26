package com.example.topshopapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_desc", nullable = false)
    private String categoryDesc;

    // Constructors
    public ProductCategory() {}

    public ProductCategory(long id, String categoryName, String categoryDesc) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDesc = categoryDesc;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
}

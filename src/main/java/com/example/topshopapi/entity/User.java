package com.example.topshopapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    @NotNull
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotNull
    @NotBlank
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "username", nullable = false, unique = true, length = 25)
    @NotBlank
    @NotNull
    @Size(min = 4, max = 25)
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank
    @NotNull
    @Size(min = 6, max = 255)
    private String password;

    @Column(name = "telephone", length = 10)
    private String telephone;

    @Column(name = "mobile", length = 10)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column
    private UserRole role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Address address;

    // Constructors
    public User() {}

    public User(long id, String firstName, String lastName, String email, String username, String password, String telephone, String mobile, UserRole role, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.mobile = mobile;
        this.role = role;
        this.address = address;
    }

    public User(String firstName, String lastName, String email, String username, String password, String telephone, String mobile, Date createdAt, UserRole role, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.mobile = mobile;
        this.address = address;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Address getUserAddress() {
        return address;
    }

    public void setUserAddress(Address address) {
        this.address = address;
    }
}

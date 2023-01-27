package com.example.topshopapi.services;

import com.example.topshopapi.entity.User;

import java.security.Principal;

public interface UserService {
    public User saveUser(User user);
    public User findByUsername(String username);
    public void deleteUserById(long id);
}

package com.example.topshopapi.services;

import com.example.topshopapi.entity.User;
import com.example.topshopapi.model.LoginBody;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    public User saveUser(User user);
    public String loginUser(LoginBody loginBody);
    public User findByUsername(String username);
    public void deleteUserById(long id);
}

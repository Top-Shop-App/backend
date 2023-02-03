package com.example.topshopapi.services.user;

import com.example.topshopapi.entity.User;
import com.example.topshopapi.exception.EmailFailureException;
import com.example.topshopapi.exception.UserNotVerifiedException;
import com.example.topshopapi.model.LoginBody;

public interface UserService {
    public User saveUser(User user) throws EmailFailureException;
    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException, EmailFailureException;
    public User findByUsername(String username);
    public boolean verifyUser(String token);
    public void deleteUserById(long id);
}

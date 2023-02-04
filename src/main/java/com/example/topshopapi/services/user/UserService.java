package com.example.topshopapi.services.user;

import com.example.topshopapi.entity.User;
import com.example.topshopapi.exception.EmailFailureException;
import com.example.topshopapi.exception.UserAlreadyExistsException;
import com.example.topshopapi.exception.UserNotVerifiedException;
import com.example.topshopapi.model.LoginBody;
import com.example.topshopapi.model.RegistrationBody;
import java.util.Optional;

public interface UserService {
    public User registerUser(RegistrationBody registrationBody) throws EmailFailureException, UserAlreadyExistsException;
    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException, EmailFailureException;
    public User findByUsername(String username);
    public boolean verifyUser(String token);
    public void deleteUserById(long id);
}

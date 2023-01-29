package com.example.topshopapi.services;

import com.auth0.jwt.JWT;
import com.example.topshopapi.entity.User;
import com.example.topshopapi.entity.UserRole;
import com.example.topshopapi.model.LoginBody;
import com.example.topshopapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final EncryptionService encryptionService;

    private JWTService jwtService;

    // Constructor
    public UserServiceImpl(EncryptionService encryptionService, JWTService jwtService) {
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public String loginUser(LoginBody loginBody){
        Optional<User> userToLogin = Optional.ofNullable(userRepository.findByUsername(loginBody.getUsername()));
        if (userToLogin.isPresent()) {
            User user = userToLogin.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

    @Override
    public User saveUser(User user) {
        // Encrypting the user's password before saving to the database.
        user.setPassword(encryptionService.encryptPassword(user.getPassword()));

        // Setting the user's role.
        user.setRole(UserRole.USER);

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}

package com.example.topshopapi.controller;

import com.example.topshopapi.entity.User;
import com.example.topshopapi.model.LoginBody;
import com.example.topshopapi.model.LoginResponse;
import com.example.topshopapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;


    // Mappings
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        try {
            // Sends a 201 status, meaning a new resource (user) has been successfully created.
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());

            return ResponseEntity.created(uri).body(userService.saveUser(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping ("/user/")
    public ResponseEntity<User> currentUser(Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        return ResponseEntity.ok().body(currentUser);
    }

    @DeleteMapping("/user/{id}/delete")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
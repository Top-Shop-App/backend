package com.example.topshopapi.controller;

import com.example.topshopapi.entity.User;
import com.example.topshopapi.entity.UserRole;
import com.example.topshopapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Sends a 201 status, meaning a new resource (user) has been created successfully.
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());

        // Encode password

        // Set a users role.
        user.setRole(UserRole.USER);
        return ResponseEntity.created(uri).body(userService.saveUser(user));
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

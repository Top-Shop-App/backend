package com.example.topshopapi.controller;

import com.example.topshopapi.entity.OrderDetails;
import com.example.topshopapi.entity.User;
import com.example.topshopapi.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDetails> getAllOrders(@AuthenticationPrincipal User user) {
        return orderService.getOrders(user);
    }
}

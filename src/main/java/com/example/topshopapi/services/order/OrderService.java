package com.example.topshopapi.services.order;

import com.example.topshopapi.entity.OrderDetails;
import com.example.topshopapi.entity.User;
import com.example.topshopapi.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    // Constructor
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Method returns all orders belonging only to the user that was passed in.
    public List<OrderDetails> getOrders(User user) {
        return orderRepository.findByUser(user);
    }
}

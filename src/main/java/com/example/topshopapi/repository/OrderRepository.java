package com.example.topshopapi.repository;

import com.example.topshopapi.entity.OrderDetails;
import com.example.topshopapi.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends ListCrudRepository<OrderDetails, Long> {

    List<OrderDetails> findByUser(User user);
}

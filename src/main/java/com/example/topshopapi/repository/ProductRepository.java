package com.example.topshopapi.repository;

import com.example.topshopapi.entity.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
}

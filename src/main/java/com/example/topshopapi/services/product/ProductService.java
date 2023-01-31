package com.example.topshopapi.services.product;

import com.example.topshopapi.entity.Product;
import com.example.topshopapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    // Constructor
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Method returns all products.
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

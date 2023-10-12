package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public Product getByID(Long id) {
        return productRepository.findByID(id);
    }

    public void deleteByID(Long id) {
        productRepository.removeByID(id);
    }

    public void edit(Product product) {
        productRepository.edit(product);
    }
}

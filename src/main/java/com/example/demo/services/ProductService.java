package com.example.demo.services;

import com.example.demo.entities.Item;
import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRep;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRep productRep;
    public List<Product> getAllProducts() {
        return productRep.findAll();
    }

    public Page<Product> getProductsWithPagingAndFiltering(Specification<Product> specs, Pageable pageable) {
        return productRep.findAll(specs, pageable);
    }

    public Long getLastId() {
        return productRep.findLastId();
    }

    public void add(Product product) { productRep.save(product); }

    public Product getByID(Long id) {
        return productRep.findById(id).get();
    }

    public void deleteByID(Long id) {
        productRep.deleteById(id);
    }


    public void edit(Product product) {
        productRep.updateProductTitle(product);
    }


}

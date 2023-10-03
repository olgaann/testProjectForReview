package com.example.demo.repositories;

import com.example.demo.entities.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Хлеб", 40));
        products.add(new Product(2L, "Кефир", 80));
        products.add(new Product(3L, "Молоко", 90));
        products.add(new Product(4L, "Хлебушек", 50));
        products.add(new Product(5L, "Хлебцы", 20));
        products.add(new Product(6L, "Похлебка", 100));
    }

    //просто эмулируем работу с базой данных, по факту просто возвращаем products:
    public List<Product> findAll() {
        return products;
    }

    public Product findByTitle (String title) {
        return products.stream().filter(product -> product.getTitle().equals(title)).findAny().get();
    }

    public Product findByID (Long id) {
        return products.stream().filter(product -> product.getId() == id).findAny().get();
    }

    public void save(Product product) {
        products.add(product);
    }

    public void remove(Product product) {
        products.removeIf(p -> p.getTitle().equals(product.getTitle()) );
    }

    public void edit(Product product) {
        Product editedProduct = products.stream().filter(p -> p.getId() == product.getId()).findAny().get();
        editedProduct.setTitle(product.getTitle());
        editedProduct.setPrice(product.getPrice());


    }
}

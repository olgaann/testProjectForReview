package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {


    @Autowired
    private ProductService productService;



    @GetMapping
    public String showProductsList(Model model) {
        Product product = new Product();
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", product);
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "delete-form";
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute(value = "product")Product product) {
        productService.delete(product);
        return "redirect:/products";
    }


    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getByID(id);
        model.addAttribute("product", product);
        return "product-page";
    }


}

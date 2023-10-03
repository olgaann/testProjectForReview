package com.example.demo.controllers;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        model.addAttribute("lastFilterByName", "");
        model.addAttribute("lastFilterUpDown", "");
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

    @GetMapping("/filter")
    public String filter(@RequestParam(value = "filterByName") String filterByName, @RequestParam(value = "filterUpDown") String filterUpDown, Model model) {
        List<Product> filteredList = productService.getAllProducts().stream()
                        .filter(product -> product.getTitle().toLowerCase().contains(filterByName.toLowerCase()))
                                .collect(Collectors.toList());

        if (filterUpDown.equals("По возрастанию")) {
            filteredList = filteredList.stream()
                    .sorted(Comparator.comparingInt(Product::getPrice))
                    .collect(Collectors.toList());
        } else if(filterUpDown.equals("По убыванию")) {
            filteredList = filteredList.stream()
                    .sorted(Comparator.comparingInt(Product::getPrice).reversed())
                    .collect(Collectors.toList());
        }

        model.addAttribute("products", filteredList);
        model.addAttribute("product", new Product());
        model.addAttribute("lastFilterByName", filterByName);
        model.addAttribute("lastFilterUpDown", filterUpDown);
        return "products";
    }


    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getByID(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @PostMapping("/edit")
    public String editProduct(Model model, @ModelAttribute(value = "product")Product product) {
        productService.edit(product);
        return "redirect:/products";
    }


}

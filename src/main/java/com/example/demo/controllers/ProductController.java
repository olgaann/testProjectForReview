package com.example.demo.controllers;

import com.example.demo.entities.Item;
import com.example.demo.entities.Product;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.specifications.ItemSpecs;
import com.example.demo.repositories.specifications.ProductSpecs;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @Autowired
    private UserService userService;

    @GetMapping
    public String showProductsList(Principal principal, Model model,
                                   @RequestParam(value = "page", required = false) Integer page,
                                   @RequestParam(value = "partOfName", required = false) String partOfName,
                                   @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                   @RequestParam(value = "maxPrice", required = false) Integer maxPrice)
    {
        if(page == null) {
            page = 1;
        }

        Specification<Product> filter = Specification.where(null);
        if(partOfName != null) {
            filter = filter.and(ProductSpecs.titleContains(partOfName));
        }
        if(minPrice != null) {
            filter = filter.and(ProductSpecs.priceGreaterThanOrEq(minPrice));
        }
        if(maxPrice != null) {
            filter = filter.and(ProductSpecs.priceLessThanOrEq(maxPrice));
        }

        if(principal != null) {
            User currentUser = userService.findByUserName(principal.getName());
            System.out.println("Авторизован пользователь: " + currentUser.toString());
        } else {
            System.out.println("Никто не авторизован");
        }

        System.out.println("Три самых частопросматриваемых товара: ");
        productService.printThreeMostPopularProducts();

        model.addAttribute("products", productService.getProductsWithPagingAndFiltering(filter, PageRequest.of(page - 1, 7)).getContent());
        model.addAttribute("partOfName", partOfName);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "products";
    }


    @GetMapping("/add")
    @Secured("ROLE_ADMIN")
    public String addProduct(Model model) {
        Product product = new Product();
        product.setId(productService.getLastId() + 1);
        model.addAttribute("product", product);
        return "add-form";

    }

    @PostMapping("/add")
    @Secured("ROLE_ADMIN")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.getByID(id);
        int qty = product.getQty();
        product.setQty(++qty);
        productService.edit(product);
        model.addAttribute("product", product);
        return "product-page";
    }

    @PostMapping("/edit")
    @Secured("ROLE_ADMIN")
    public String editProduct(Principal principal, @ModelAttribute(value = "product")Product product) {
        productService.edit(product);
//        if (principal != null) {
//            User currentUser = userService.findByUserName(principal.getName());
//            Collection<Role> roles = currentUser.getRoles();
//            boolean containsAdminRole = roles.stream()
//                    .anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
//            if(containsAdminRole) {
//                productService.edit(product);
//            }
//        }
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteOneProduct(@PathVariable(value = "id") Long id) {
        productService.deleteByID(id);
        return "redirect:/products";
    }

  

}

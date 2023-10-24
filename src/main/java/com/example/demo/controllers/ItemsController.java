package com.example.demo.controllers;

import com.example.demo.entities.Item;
import com.example.demo.repositories.specifications.ItemSpecs;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String showItemsPage(Model model) {
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/sorted")
    public String showSortedItemsPage(Model model) {
        Specification<Item> filter = Specification.where(null);
        filter = filter.and(ItemSpecs.costGreaterThanOrEq(100));

        //Specification<Item> filter = ItemSpecs.costGreaterThanOrEq(30);
        List<Item> resultList = itemService.getItemsWithPagingAndFiltering(filter, PageRequest.of(1, 2)).getContent();
        model.addAttribute("items", resultList);
        return "items";
    }
}

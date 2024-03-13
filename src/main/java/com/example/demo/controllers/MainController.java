package com.example.demo.controllers;

import com.example.demo.entities.ItemProjection;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.ProductRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/main")
public class MainController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/index")
    public String doSomething() {
        List<ItemProjection> list = itemRepository.findItemsProjectionsByCost(10);
        list.stream().map(itemProjection -> itemProjection.getTitle() + " " + itemProjection.getCost()).forEach(System.out::println);
        return "simple-form";
    }


    @GetMapping("/hello")
     //@ResponseBody //эта аннотация вернет java-объект
    public String helloRequest(Model model, @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("form")
    public String showForm()   {
        return "simple-form";
    }

    @PostMapping("/form")

    public String saveForm(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email) {
        System.out.println(name + " " + email);
        return "index";
    }

    @GetMapping("/addcat")
    public String showAddCatForm(Model model)   {
        Cat kitten = new Cat(1L, "Barsik", "Grey");
        Cat cat = new Cat();
        model.addAttribute("cat", cat);
        return "cat-form";
    }

    @PostMapping("/addcat")
    public String showAddCatForm(@ModelAttribute(value = "cat") Cat kitten) {
        System.out.println(kitten.toString());
        return "cat-form";
    }





































}

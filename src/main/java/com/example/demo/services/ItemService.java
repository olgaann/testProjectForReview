package com.example.demo.services;

import com.example.demo.entities.Item;
import com.example.demo.entities.ItemProjection;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.repositories.ItemSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemRepository itemRepository;
    private ItemSortingRepository itemSortingRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @Autowired
    public void setItemSortingRepository(ItemSortingRepository itemSortingRepository) {
        this.itemSortingRepository = itemSortingRepository;
    }




    public List<Item> getAllItems() {
        System.out.println(itemRepository.findByTitle("tree"));
        List<Item> list = itemRepository.myMethod();
        for (Item item : list) {
            System.out.println(item);
        }

        //препод что-то пытался сделать, но не получилось

//        List<ItemProjection> list1 = itemRepository.getProjections();
//        System.out.println("список ItemProjection");
//        for (ItemProjection itemProjection : list1) {
//            System.out.println(itemProjection.title() + " " + itemProjection.cost());
//        }
        return itemRepository.findAll();
    }

    public Page<Item> getItemsWithPagingAndFiltering(Specification<Item> specs, Pageable pageable) {
        return itemSortingRepository.findAll(specs, pageable);
    }
















}

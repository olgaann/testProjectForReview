package com.example.demo.repositories;

import com.example.demo.entities.Item;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

//Включаем пагинацию и сортировку PagingAndSortingRepository<Item, Long>
// и спецификации JpaSpecificationExecutor<Item>
public interface ItemSortingRepository  extends PagingAndSortingRepository<Item, Long>, JpaSpecificationExecutor<Item> {

}

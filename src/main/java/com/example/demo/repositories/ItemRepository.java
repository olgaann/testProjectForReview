package com.example.demo.repositories;

import com.example.demo.entities.Item;
import com.example.demo.entities.ItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Этот интерфейс надо унаследовать от JpaRepository
//JpaRepository это обобщенный интерфейс
// Здесь надо указать тип объекта, с которым мы собирается работать - Item,
// а также тип айдишки класса Item - Long
//Теперь вся работа с этими Item-ами будет "из коробки"
// также может наследоваться от extends PagingAndSortingRepository<Item, Long>
// нужно для разбивки данных на страницы (лекция 1:58:00)
@Repository

public interface ItemRepository extends JpaRepository<Item, Long> {

    //спринг дата позволяет формировать запросы путем спец структуры имен методов
    //вы метод как-то назыаете и он будет выполнять спец запрос:
    Item findByTitle(String title); //то есть мы не прописали никакой реализации этому методу
    //но несмотря на это спринг распарсил название findByTitle(String title) и выполнил соотв.запрос

    List<Item> findByCostBetween(int min, int max);

    List<Item> findByCostGreaterThan(int max);

    // а если хочется создать свой метод?
    @Query(value = "SELECT i FROM Item i WHERE i.cost > 40")
    //@Query(value = "SELECT i FROM Item i WHERE i.cost > 40", nativeQuery = true)
    //nativeQuery = true означает, что это не jpql запрос, а чистый sql
    List<Item> myMethod();

    //List<ItemProjection> getProjections();
}

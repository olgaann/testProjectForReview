package com.example.demo.repositories;

import com.example.demo.entities.ItemProjection;
import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface ProductRep extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findTop3ByOrderByQtyDesc();


    @Query(value = "SELECT MAX(id) FROM Product")
    Long findLastId();

    //добавляй @Transactional при UPDATE
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.title = :#{#product.getTitle()}, p.price = :#{#product.getPrice()}, p.qty = :#{#product.getQty()} WHERE p.id = :#{#product.getId()}")
    void updateProduct(@Param("product") Product product);

//    @Transactional
//    @Query(value = "SELECT * FROM products ORDER BY qty DESC LIMIT 3", nativeQuery = true)
//    List<Product> findThreeMostPopularProducts();


    @Transactional
    @Query(value = "SELECT p FROM Product p ORDER BY p.qty DESC")
    List<Product> findThreeMostPopularProducts() ;

}

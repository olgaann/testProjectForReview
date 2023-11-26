package com.example.demo.repositories;

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
import java.util.List;

@Repository
public interface ProductRep extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    public List<Product> findAll();
    public Product save(Product product);
    public void deleteById(Long id);


    @Query(value = "SELECT MAX(id) FROM Product")
    Long findLastId();

//    @Transactional
//    @Modifying
//    @Query("UPDATE Product p SET p.title = :title, p.price = :price WHERE p.id = :id")
//    void updateProductTitle(@Param("id") Long id, @Param("title") String title, @Param("price") int price);

    //добавляй @Transactional при UPDATE
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.title = :#{#product.getTitle()}, p.price = :#{#product.getPrice()}, p.qty = :#{#product.getQty()} WHERE p.id = :#{#product.getId()}")
    void updateProduct(@Param("product") Product product);

}

package com.acro.lab.ecommerce.repository;

import com.acro.lab.ecommerce.dto.ProductProjection;
import com.acro.lab.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value="select * from product as p" +
            " where p.name like %:name%", nativeQuery = true)
    List<Product> findProductByName(@Param("name") String name);


    @Query(value ="select p.id, p.name, p.description, p.price, p.quantity, c.name categoryName " +
            "from product p " +
            "join category c on p.category_id = c.id " +
            "where c.id = :categoryId", nativeQuery = true)
    List<ProductProjection> findProductByCategoryId(@Param("categoryId") Long categoryId);
}


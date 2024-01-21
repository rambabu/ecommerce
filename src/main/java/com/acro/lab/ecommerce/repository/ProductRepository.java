package com.acro.lab.ecommerce.repository;

import com.acro.lab.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value="select * from product as p\n" +
            "where p.name like ':name'", nativeQuery = true)
    Product findProductByName(String name);  //inbuilt jpa methods


}


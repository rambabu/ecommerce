package com.acro.lab.ecommerce.repository;

import com.acro.lab.ecommerce.entity.Category;
import com.acro.lab.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}

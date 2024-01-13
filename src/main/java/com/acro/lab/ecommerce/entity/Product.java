package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name="product")
public class Product extends BaseEntity {
    @Column(name="category_id")
    Long categoryId;
    @Column(name="product_name")
    String productName;
    @Column(name ="product_description")
    String productDescription;
    float price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id",referencedColumnName = "id")//mp-1c
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Float.compare(price, product.price) == 0 && Objects.equals(categoryId, product.categoryId) && Objects.equals(productName, product.productName) && Objects.equals(productDescription, product.productDescription) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoryId, productName, productDescription, price, category);
    }
}

package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table
public class Product extends BaseEntity{
   @Column(name="name")
   private String name;
   @Column(name="description")
   private String description;
   @Column(name="price")
   private double price;

   @ManyToOne
   @JoinColumn(name="category_id")
   private Category category;

   @OneToOne(mappedBy ="product")
   private ProductInventory productInventory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getPrice(), product.getPrice()) == 0 && Objects.equals(getName(), product.getName()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getCategory(), product.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getDescription(), getPrice(), getCategory());
    }
}

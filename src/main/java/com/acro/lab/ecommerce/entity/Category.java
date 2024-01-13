package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name="category")
public class Category extends BaseEntity{


    private String name;
    private String description;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="modified_at")
    private LocalDateTime modifiedAt;
    private String status;
    @OneToMany(mappedBy="category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)//1c-mp
    private Set<Product> product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(description, category.description) && Objects.equals(createdAt, category.createdAt) && Objects.equals(modifiedAt, category.modifiedAt) && Objects.equals(status, category.status) && Objects.equals(product, category.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, createdAt, modifiedAt, status, product);
    }
}

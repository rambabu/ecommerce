package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table
public class Category extends BaseEntity{
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="created_at")
    private LocalDate createdAt;
    @Column(name="is_active")
    private boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        if (!super.equals(o)) return false;
        return isActive() == category.isActive() && Objects.equals(getName(), category.getName()) && Objects.equals(getDescription(), category.getDescription()) && Objects.equals(getCreatedAt(), category.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getDescription(), getCreatedAt(), isActive());
    }
}

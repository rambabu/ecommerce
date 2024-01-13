package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
@Entity
@Data
@Table(name="user_cart")
public class UserCart extends BaseEntity {
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCart userCart)) return false;
        if (!super.equals(o)) return false;
        return getQuantity() == userCart.getQuantity() && Objects.equals(getCreatedAt(), userCart.getCreatedAt()) && Objects.equals(getUser(), userCart.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getQuantity(), getCreatedAt(), getUser());
    }
}
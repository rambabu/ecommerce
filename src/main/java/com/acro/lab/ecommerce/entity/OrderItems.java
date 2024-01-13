package com.acro.lab.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table
public class OrderItems extends BaseEntity{
   private int quantity;
   private LocalDateTime createdAt;
   private LocalDateTime modifiedAt;

   @ManyToOne
   @JoinColumn(name="order_id")
    private OrderDetails orderDetails ;

   @ManyToOne
   @JoinColumn(name="product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItems that)) return false;
        if (!super.equals(o)) return false;
        return quantity == that.quantity && Objects.equals(createdAt, that.createdAt) && Objects.equals(modifiedAt, that.modifiedAt) && Objects.equals(orderDetails, that.orderDetails) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, createdAt, modifiedAt, orderDetails, product);
    }
}

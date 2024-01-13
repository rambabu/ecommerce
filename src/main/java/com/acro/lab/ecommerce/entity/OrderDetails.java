package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="order_details")
@Data
public class OrderDetails extends BaseEntity{

   @Column(name="total")
   private double total;
   @Column(name="created_at")
   private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(mappedBy = "orderDetails")
    private PaymentDetails paymentDetails;

   @OneToMany(mappedBy = "orderDetails")
   private List<OrderItems> orderItems;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getTotal(), that.getTotal()) == 0 && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getUser(), that.getUser()) && Objects.equals(getPaymentDetails(), that.getPaymentDetails()) && Objects.equals(getOrderItems(), that.getOrderItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTotal(), getCreatedAt(), getUser(), getPaymentDetails(), getOrderItems());
    }
}

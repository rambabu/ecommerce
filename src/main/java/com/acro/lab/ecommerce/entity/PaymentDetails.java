package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@Table(name="payment_details")
public class PaymentDetails extends BaseEntity{

    @Column(name="amount")
    private Double amount;
    @Column(name="payment_type")
    private String paymentType;
    @Column(name="status")
    private String status;
    @Column(name = "created_at")
    private LocalDate createdAt;

    @OneToOne
    @JoinColumn(name="order_id")
    private OrderDetails orderDetails;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentDetails that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAmount(), that.getAmount()) && Objects.equals(getPaymentType(), that.getPaymentType()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getOrderDetails(), that.getOrderDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAmount(), getPaymentType(), getStatus(), getCreatedAt(), getOrderDetails());
    }
}

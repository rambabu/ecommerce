package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table
public class UserPayment extends BaseEntity{
    @Column(name="payment_type")
    private String paymentType;
    @Column(name=" account_no")
    private int accountNo;
    @Column(name="expiry")
    private LocalDateTime expiry;

    @ManyToOne
    @JoinColumn(name=" user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPayment that)) return false;
        if (!super.equals(o)) return false;
        return getAccountNo() == that.getAccountNo() && Objects.equals(getPaymentType(), that.getPaymentType()) && Objects.equals(getExpiry(), that.getExpiry()) && Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPaymentType(), getAccountNo(), getExpiry(), getUser());
    }
}

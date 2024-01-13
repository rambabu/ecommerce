package com.acro.lab.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name="user_payment")
public class UserPayment extends BaseEntity{
    @Column(name="user_id")
    private Long userId;
    @Column(name="payment_type")
    private String paymentType;
    @Column(name="account_no")
    private Long accountNumber;
    private LocalDate expiry;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserPayment that = (UserPayment) o;
        return Objects.equals(userId, that.userId) && Objects.equals(paymentType, that.paymentType) && Objects.equals(accountNumber, that.accountNumber) && Objects.equals(expiry, that.expiry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, paymentType, accountNumber, expiry);
    }
}

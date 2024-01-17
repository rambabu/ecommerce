package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table
public class User extends BaseEntity {
    @Column(name="name")
    private String UserName;
    @Column(name="password")
    private String password;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="contact")
    private int contact;
    @Column(name="address")
    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetailsList;

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    private List<UserCart> userCartList;

    @OneToMany(mappedBy = "user", fetch= FetchType.LAZY)
    private List<UserPayment> userPaymentList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
        return getContact() == user.getContact() && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getOrderDetailsList(), user.getOrderDetailsList()) && Objects.equals(getUserCartList(), user.getUserCartList()) && Objects.equals(getUserPaymentList(), user.getUserPaymentList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUserName(), getPassword(), getFirstName(), getLastName(), getContact(), getAddress(), getOrderDetailsList(), getUserCartList(), getUserPaymentList());
    }
}

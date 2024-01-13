package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name="user")
public class User extends BaseEntity {
    @Column(name="user_name")
    String userName;

    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)//1u-mo
     private List<Order>orderList;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch = FetchType.LAZY)//1U-MA
    private List<UserAddress>userAddressList;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)//1U-MP
    private List<UserPayment>userPaymentList;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(orderList, user.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userName, orderList);
    }
}

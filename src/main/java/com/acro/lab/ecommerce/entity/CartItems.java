package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name="cart")
public class CartItems extends BaseEntity{
    @Column(name="user_id")
    Long userId;
    @Column(name="delivery_date")
    LocalDateTime deliveryDate;
    //mci-1p--relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id",referencedColumnName = "id")
    private Product product;
    //mci-1s
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName="id")
    private User user;


    //1ci-1o//doubt
    /*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="order_id",referencedColumnName="id")
    private Order order;*/
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id",referencedColumnName="id")//mo-1s
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CartItems cartItems = (CartItems) o;
        return Objects.equals(userId, cartItems.userId) && Objects.equals(deliveryDate, cartItems.deliveryDate) && Objects.equals(product, cartItems.product) && Objects.equals(user, cartItems.user) && Objects.equals(order, cartItems.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, deliveryDate, product, user, order);
    }
}

package com.acro.lab.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name="order")
public class Order extends BaseEntity {
    @Column(name="cart_id")
    Long cartId;
    @Column(name="product_id")
    Long productId;
    float quantity;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName="id")//mo-1s
    private User user;

     @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "order")
    private List<CartItems> cartItemsList;//1O-MCI

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Float.compare(quantity, order.quantity) == 0 && Objects.equals(cartId, order.cartId) && Objects.equals(productId, order.productId) && Objects.equals(user, order.user) && Objects.equals(cartItemsList, order.cartItemsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cartId, productId, quantity, user, cartItemsList);
    }
}

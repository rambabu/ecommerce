package com.acro.lab.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name="review")
public class UserReview extends BaseEntity{
    @Column(name="cart_id")
    private Long cartId;
    @Column(name="product_id")
    private Long productId;
    @Column(name="user_id")
    private Long userId;
    private Long rating;
    private String comment;
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserReview review = (UserReview) o;
        return Objects.equals(cartId, review.cartId) && Objects.equals(productId, review.productId) && Objects.equals(userId, review.userId) && Objects.equals(rating, review.rating) && Objects.equals(comment, review.comment) && Objects.equals(createdAt, review.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cartId, productId, userId, rating, comment, createdAt);
    }
}

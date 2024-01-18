package com.acro.lab.ecommerce.repository;

import com.acro.lab.ecommerce.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<UserReview,Long> {
}

package com.acro.lab.ecommerce.repository;

import com.acro.lab.ecommerce.entity.UserPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentRepository extends JpaRepository<UserPayment,Long> {
}

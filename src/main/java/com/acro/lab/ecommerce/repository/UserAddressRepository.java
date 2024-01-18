package com.acro.lab.ecommerce.repository;

import com.acro.lab.ecommerce.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {
}

package com.sarena.posbackend.repository;

import com.sarena.posbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

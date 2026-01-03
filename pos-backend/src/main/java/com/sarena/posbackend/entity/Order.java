package com.sarena.posbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "order_name", nullable = false)
    private String orderName;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // ===== Getters & Setters =====

}


package com.sarena.posbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "order_items")
public class OrderItems {

    // ===== Getters & Setters =====
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // from input
    @Setter
    private LocalDateTime date;

    @Setter
    @Column(nullable = false)
    private String customerName;

    @Setter
    @Column(nullable = false)
    private String paymentMethod;

    // product
    @Setter
    @Column(nullable = false)
    private Long productId;

    @Setter
    @Column(nullable = false)
    private String productName;

    // numbers
    @Setter
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal quantityKg;

    @Setter
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal pricePerUnit;

    @Setter
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal lineTotal;

    @Setter
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal unpaid;

    @Setter
    private String note;

    // ===== REQUIRED by JPA =====
    public OrderItems() {}

}

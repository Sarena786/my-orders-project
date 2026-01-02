package com.sarena.posbackend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // from input
    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String paymentMethod;

    // product
    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    // numbers
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal quantityKg;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal pricePerUnit;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal lineTotal;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal unpaid;

    // ===== REQUIRED by JPA =====
    public OrderItems() {}

    // ===== Getters & Setters =====
    public Long getId() { return id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public BigDecimal getQuantityKg() { return quantityKg; }
    public void setQuantityKg(BigDecimal quantityKg) { this.quantityKg = quantityKg; }

    public BigDecimal getPricePerUnit() { return pricePerUnit; }
    public void setPricePerUnit(BigDecimal pricePerUnit) { this.pricePerUnit = pricePerUnit; }

    public BigDecimal getLineTotal() { return lineTotal; }
    public void setLineTotal(BigDecimal lineTotal) { this.lineTotal = lineTotal; }

    public BigDecimal getUnpaid() { return unpaid; }
    public void setUnpaid(BigDecimal unpaid) { this.unpaid = unpaid; }
}

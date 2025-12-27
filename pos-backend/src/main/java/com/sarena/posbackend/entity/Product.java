package com.sarena.posbackend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(precision = 10, scale = 2)
    private BigDecimal pricePerKg;

    @Column(precision = 10, scale = 2)
    private BigDecimal quantity;

    private String note;

    private String imageUrl;

    public Product() {}

    public Product(String name, BigDecimal pricePerKg, BigDecimal quantity, String note, String imageUrl) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.quantity = quantity;
        this.note = note;
        this.imageUrl = imageUrl;
    }

    // getters & setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPricePerKg() { return pricePerKg; }
    public void setPricePerKg(BigDecimal pricePerKg) { this.pricePerKg = pricePerKg; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    @Transient
    public BigDecimal getTotalPrice() {
        if (pricePerKg == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return pricePerKg.multiply(quantity);
    }
}


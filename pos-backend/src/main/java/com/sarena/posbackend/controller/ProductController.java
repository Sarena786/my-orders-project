package com.sarena.posbackend.controller;

import com.sarena.posbackend.entity.Product;
import com.sarena.posbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }

    @PostMapping
    public Product create(
            @RequestParam String name,
            @RequestParam Double pricePerKg,
            @RequestParam Double quantity,
            @RequestParam(required = false) String note
    ) {

        // 5️⃣ Save DB record
        Product p = new Product();
        p.setName(name);
        p.setPricePerKg(BigDecimal.valueOf(pricePerKg));
        p.setQuantity(BigDecimal.valueOf(quantity));
        p.setNote(note);

        return productService.save(p);
    }

    @PutMapping("/{id}")
    public Product update(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}

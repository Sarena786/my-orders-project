package com.sarena.posbackend.controller;

import com.sarena.posbackend.entity.Product;
import com.sarena.posbackend.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // CREATE product with image upload
    @PostMapping(consumes = "multipart/form-data")
    public Product create(
            @RequestParam("name") String name,
            @RequestParam("pricePerKg") BigDecimal pricePerKg,
            @RequestParam("quantity") BigDecimal quantity,
            @RequestParam(value = "note", required = false) String note,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        // 1️⃣ Create upload directory if not exists
        Path uploadDir = Paths.get("uploads/products");
        Files.createDirectories(uploadDir);

        // 2️⃣ Generate unique filename
        String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();

        // 3️⃣ Save image to disk
        Path filePath = uploadDir.resolve(filename);
        image.transferTo(filePath.toFile());

        // 4️⃣ Save product with image URL
        Product product = new Product(
                name,
                pricePerKg,
                quantity,
                note,
                "/uploads/products/" + filename
        );

        return productService.save(product);
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

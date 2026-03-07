package com.example.NgoDangKhoa_2280601515.controller;

import com.example.NgoDangKhoa_2280601515.model.Category;
import com.example.NgoDangKhoa_2280601515.model.Product;
import com.example.NgoDangKhoa_2280601515.repository.CategoryRepository;
import com.example.NgoDangKhoa_2280601515.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            if (p.getCategory() != null) {
                p.setCategoryName(p.getCategory().getName());
            }
        }
        return products;
    }

    @PostMapping("/sync")
    public ResponseEntity<?> syncProducts(@RequestBody List<ProductDTO> products) {
        try {
            productRepository.deleteAll();
        } catch (Exception e) {
            System.err.println("Could not delete all products, some might be in use.");
        }

        for (ProductDTO dto : products) {
            Product product = new Product();
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setOldPrice(dto.getOldPrice());
            product.setDiscount(dto.getDiscount());
            product.setImage(dto.getImage());
            product.setLink(dto.getLink());
            product.setPromotion(dto.getPromotion() != null && dto.getPromotion());

            if (dto.getCategory() != null && !dto.getCategory().isEmpty()) {
                Category cat = categoryRepository.findAll().stream()
                        .filter(c -> c.getName() != null && c.getName().equalsIgnoreCase(dto.getCategory()))
                        .findFirst()
                        .orElse(null);
                product.setCategory(cat);
                product.setCategoryName(dto.getCategory());
            }
            productRepository.save(product);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setOldPrice(dto.getOldPrice());
        product.setDiscount(dto.getDiscount());
        product.setImage(dto.getImage());
        product.setLink(dto.getLink());
        product.setPromotion(dto.getPromotion() != null && dto.getPromotion());

        if (dto.getCategory() != null && !dto.getCategory().isEmpty()) {
            Category cat = categoryRepository.findAll().stream()
                    .filter(c -> c.getName() != null && c.getName().equalsIgnoreCase(dto.getCategory()))
                    .findFirst()
                    .orElse(null);
            product.setCategory(cat);
            product.setCategoryName(dto.getCategory());
        }
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

class ProductDTO {
    private String name;
    private double price;
    private double oldPrice;
    private double discount;
    private String image;
    private Boolean promotion;
    private String category;
    private String link;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getPromotion() {
        return promotion;
    }

    public void setPromotion(Boolean promotion) {
        this.promotion = promotion;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

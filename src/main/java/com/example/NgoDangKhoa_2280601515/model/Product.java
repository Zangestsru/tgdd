package com.example.NgoDangKhoa_2280601515.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String description;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String image; // URL hình ảnh sản phẩm

    private Boolean promotion; // Flag cho sản phẩm khuyến mãi online

    private double oldPrice;
    private double discount;
    private String link;

    @Transient
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
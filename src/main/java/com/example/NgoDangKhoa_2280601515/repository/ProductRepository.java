package com.example.NgoDangKhoa_2280601515.repository;

import com.example.NgoDangKhoa_2280601515.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPromotionTrue();

    List<Product> findByPromotionFalse();
}
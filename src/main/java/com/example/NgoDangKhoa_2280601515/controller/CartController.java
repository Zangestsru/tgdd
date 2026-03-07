package com.example.NgoDangKhoa_2280601515.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String viewCart() {
        return "cart/cart";
    }
}

package com.example.ngogiaphuong2280602523.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String viewCart() {
        return "cart/cart";
    }
}


package com.example.NgoDangKhoa_2280601515.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/categories")
    public String adminCategoriesPage() {
        return "admin-categories";
    }
}

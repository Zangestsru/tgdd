package com.example.ngogiaphuong2280602523.controller;

import com.example.ngogiaphuong2280602523.model.Category;
import com.example.ngogiaphuong2280602523.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Hiá»ƒn thá»‹ danh sĂ¡ch danh má»¥c
    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories/list";
    }

    // Hiá»ƒn thá»‹ form thĂªm danh má»¥c má»›i
    @GetMapping("/new")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/form";
    }

    // LÆ°u danh má»¥c (thĂªm má»›i hoáº·c cáº­p nháº­t)
    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, 
                               BindingResult result) {
        if (result.hasErrors()) {
            return "categories/form";
        }
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    // Hiá»ƒn thá»‹ form chá»‰nh sá»­a danh má»¥c
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("category", category);
        return "categories/form";
    }

    // XĂ³a danh má»¥c
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        categoryRepository.delete(category);
        return "redirect:/categories";
    }
}


package com.example.ngogiaphuong2280602523.controller;

import com.example.ngogiaphuong2280602523.model.Product;
import com.example.ngogiaphuong2280602523.repository.ProductRepository;
import com.example.ngogiaphuong2280602523.repository.CategoryRepository;
import com.example.ngogiaphuong2280602523.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileStorageService fileStorageService;

    // Hiá»ƒn thá»‹ danh sĂ¡ch sáº£n pháº©m
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("promotionProducts", productRepository.findByPromotionTrue());
        model.addAttribute("regularProducts", productRepository.findByPromotionFalse());
        model.addAttribute("products", productRepository.findAll()); // DĂ nh cho cĂ¡c view cÅ© dĂ¹ng chung "products"
        return "products/ProductsList";
    }

    // Hiá»ƒn thá»‹ form thĂªm sáº£n pháº©m má»›i
    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "products/form";
    }

    // LÆ°u sáº£n pháº©m (thĂªm má»›i hoáº·c cáº­p nháº­t)
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            // Xá»­ lĂ½ upload file áº£nh
            if (imageFile != null && !imageFile.isEmpty()) {
                // Kiá»ƒm tra file cĂ³ pháº£i lĂ  áº£nh khĂ´ng
                if (fileStorageService.isImageFile(imageFile)) {
                    // XĂ³a áº£nh cÅ© náº¿u Ä‘ang cáº­p nháº­t sáº£n pháº©m
                    if (product.getId() != null) {
                        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
                        if (existingProduct != null && existingProduct.getImage() != null) {
                            fileStorageService.deleteFile(existingProduct.getImage());
                        }
                    }
                    // LÆ°u file má»›i vĂ  cáº­p nháº­t Ä‘Æ°á»ng dáº«n
                    String filePath = fileStorageService.storeFile(imageFile);
                    product.setImage(filePath);
                }
            }

            // TĂ¬m Category tá»« database vĂ  gĂ¡n vĂ o Product
            if (categoryId != null) {
                categoryRepository.findById(categoryId).ifPresent(product::setCategory);
            } else {
                product.setCategory(null);
            }

            if (product.getFlashSaleQuantity() == null) {
                product.setFlashSaleQuantity(0);
            }

            productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            // CĂ³ thá»ƒ thĂªm thĂ´ng bĂ¡o lá»—i cho user
        }
        return "redirect:/products";
    }

    // Hiá»ƒn thá»‹ form chá»‰nh sá»­a sáº£n pháº©m
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        return "products/form";
    }

    // XĂ³a sáº£n pháº©m
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        // XĂ³a file áº£nh trÆ°á»›c khi xĂ³a sáº£n pháº©m
        if (product.getImage() != null) {
            fileStorageService.deleteFile(product.getImage());
        }

        productRepository.delete(product);
        return "redirect:/products";
    }
}


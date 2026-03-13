package com.example.ngogiaphuong2280602523.controller;

import com.example.ngogiaphuong2280602523.model.Order;
import com.example.ngogiaphuong2280602523.model.Product;
import com.example.ngogiaphuong2280602523.model.OrderStatus;
import com.example.ngogiaphuong2280602523.repository.OrderRepository;
import com.example.ngogiaphuong2280602523.repository.ProductRepository;
import com.example.ngogiaphuong2280602523.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    // Hiá»ƒn thá»‹ danh sĂ¡ch Ä‘Æ¡n hĂ ng
    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
        return "orders/list";
    }

    // Hiá»ƒn thá»‹ form táº¡o Ä‘Æ¡n hĂ ng má»›i
    @GetMapping("/new")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("statuses", OrderStatus.values());
        return "orders/form";
    }

    // LÆ°u Ä‘Æ¡n hĂ ng (thĂªm má»›i hoáº·c cáº­p nháº­t)
    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderService.placeOrder(order);
        return "redirect:/orders";
    }

    // Hiá»ƒn thá»‹ chi tiáº¿t Ä‘Æ¡n hĂ ng
    @GetMapping("/view/{id}")
    public String viewOrder(@PathVariable("id") Long id, Model model) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        return "orders/view";
    }

    // Hiá»ƒn thá»‹ form chá»‰nh sá»­a Ä‘Æ¡n hĂ ng
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("statuses", OrderStatus.values());
        return "orders/form";
    }

    // Cáº­p nháº­t tráº¡ng thĂ¡i Ä‘Æ¡n hĂ ng
    @PostMapping("/update-status/{id}")
    public String updateOrderStatus(@PathVariable("id") Long id, 
                                    @RequestParam("status") OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        order.setStatus(status);
        orderRepository.save(order);
        return "redirect:/orders";
    }

    // XĂ³a Ä‘Æ¡n hĂ ng
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        orderRepository.delete(order);
        return "redirect:/orders";
    }
}


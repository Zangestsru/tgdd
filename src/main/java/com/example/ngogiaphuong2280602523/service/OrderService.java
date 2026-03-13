package com.example.ngogiaphuong2280602523.service;

import com.example.ngogiaphuong2280602523.model.Order;
import com.example.ngogiaphuong2280602523.model.Product;
import com.example.ngogiaphuong2280602523.repository.OrderRepository;
import com.example.ngogiaphuong2280602523.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order placeOrder(Order order) {
        if (order.getProducts() == null || order.getProducts().isEmpty()) {
            throw new RuntimeException("Đơn hàng không có sản phẩm");
        }

        // 1. Load real products from DB and handle flash sale stock
        List<Product> productsFromDb = new ArrayList<>();
        double subtotal = 0;
        int totalQuantity = order.getProducts().size();

        for (Product p : order.getProducts()) {
            Product dbProduct = productRepository.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm id: " + p.getId()));
            
            productsFromDb.add(dbProduct);
            subtotal += dbProduct.getPrice();

            // Flash sale logic: check and decrement quantity
            if (Boolean.TRUE.equals(dbProduct.getPromotion())) {
                int currentQty = dbProduct.getFlashSaleQuantity() != null ? dbProduct.getFlashSaleQuantity() : 0;
                if (currentQty <= 0) {
                    throw new RuntimeException("Sản phẩm '" + dbProduct.getName() + "' đã hết suất Flash Sale!");
                }
                
                dbProduct.setFlashSaleQuantity(currentQty - 1);
                // If stock reaches 0, remove from flash sale
                if (dbProduct.getFlashSaleQuantity() == 0) {
                    dbProduct.setPromotion(false);
                }
                productRepository.save(dbProduct);
            }
        }

        order.setProducts(productsFromDb);

        // 2. Shipping Fee Logic
        // >= 1tr and quantity >= 2 -> Free ship
        // Else -> 30k shipping + 1 reward point
        if (subtotal >= 1000000 && totalQuantity >= 2) {
            order.setShippingFee(0);
            // Reward points logic is not explicitly defined for free ship case, 
            // but the requirement says "ngược lại 30k+1điểm", implying points are earned when paying shipping.
            order.setRewardPoints(0); 
        } else {
            order.setShippingFee(30000);
            order.setRewardPoints(1);
        }

        order.setTotalAmount(subtotal + order.getShippingFee());

        return orderRepository.save(order);
    }
}

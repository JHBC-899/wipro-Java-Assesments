package com.example.orderServices.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderServices.dto.OrderRequest;
import com.example.orderServices.dto.PaymentRequest;
import com.example.orderServices.dto.PaymentResponse;
import com.example.orderServices.dto.ProductResponse;
import com.example.orderServices.entity.Order;
import com.example.orderServices.entity.OrderStatus;
import com.example.orderServices.feign.PaymentClient;
import com.example.orderServices.feign.ProductServiceClient;
import com.example.orderServices.repository.OrderRepository;



@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductServiceClient productClient;

    @Autowired
    private PaymentClient paymentClient;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {
        // 1. Check product availability
        ProductResponse product = productClient.getProduct(request.getProductId());
        if (product == null || product.getStock() < request.getQuantity()) {
            return ResponseEntity.badRequest().body("Product unavailable or insufficient stock.");
        }

        // 2. Create order (initially pending payment)
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setPrice(product.getPrice()); // price per item
        order.setOrderStatus(OrderStatus.PENDING_PAYMENT);
        order.setOrderDate(LocalDateTime.now());
        orderRepo.save(order);

        PaymentRequest paymentReq = new PaymentRequest();
        paymentReq.setOrderId(order.getId());
        paymentReq.setAmount(product.getPrice() * request.getQuantity()); // total
        paymentReq.setPaymentMethod(request.getPaymentMethod());

        PaymentResponse paymentResp = paymentClient.makePayment(paymentReq);

        // 4. Update order status & reduce stock if payment successful
        if ("SUCCESS".equalsIgnoreCase(paymentResp.getStatus())) {
            order.setOrderStatus(OrderStatus.CONFIRMED);
            productClient.reduceStock(product.getId(), request.getQuantity());
        } else {
            order.setOrderStatus(OrderStatus.CANCELLED);
        }
        orderRepo.save(order);

        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer orderId) {
        return orderRepo.findById(orderId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Integer orderId, @RequestBody OrderRequest request) {
        Optional<Order> existingOrderOpt = orderRepo.findById(orderId);
        if (existingOrderOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProductResponse product = productClient.getProduct(request.getProductId());
        if (product == null || product.getStock() < request.getQuantity()) {
            return ResponseEntity.badRequest().body("Product unavailable or insufficient stock for update.");
        }

        Order order = existingOrderOpt.get();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setPrice(product.getPrice());
        order.setOrderStatus(OrderStatus.UPDATED);
        orderRepo.save(order);

        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer orderId) {
        Optional<Order> order = orderRepo.findById(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        orderRepo.deleteById(orderId);
        return ResponseEntity.ok("Order with ID " + orderId + " has been cancelled and deleted.");
    }
}

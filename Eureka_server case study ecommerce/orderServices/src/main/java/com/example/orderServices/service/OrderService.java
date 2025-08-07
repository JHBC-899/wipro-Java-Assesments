package com.example.orderServices.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.orderServices.dto.OrderRequest;
import com.example.orderServices.dto.ProductResponse;
import com.example.orderServices.entity.Order;
import com.example.orderServices.entity.OrderStatus;
import com.example.orderServices.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    public Order placeOrder(OrderRequest orderRequest) {
        // 1. Check product availability from product-service
        ProductResponse product = restTemplate.getForObject(
                "http://localhost:8081/api/products/" + orderRequest.getProductId(),
                ProductResponse.class
        );

        if (product == null || product.getStock() < orderRequest.getQuantity()) {
            throw new RuntimeException("Product unavailable or insufficient stock.");
        }

        // 2. Calculate total price
        double totalPrice = product.getPrice() * orderRequest.getQuantity();

        // 3. Create order with PENDING_PAYMENT status
        
        Order order=new Order(
        		orderRequest.getProductId(),
        		orderRequest.getQuantity(),
        		totalPrice,
        		OrderStatus.PENDING_PAYMENT);
        orderRepository.save(order);

        // 4. Call payment-service
        boolean paymentSuccess = restTemplate.postForObject(
                "http://localhost:8082/api/payments",
                totalPrice,
                Boolean.class
        );

        if (Boolean.TRUE.equals(paymentSuccess)) {
            order.setOrderStatus(OrderStatus.CONFIRMED);
            orderRepository.save(order);

            // 5. Reduce stock in product-service
            restTemplate.put(
                    "http://localhost:8081/api/products/reduce-stock/" +
                    order.getProductId() + "/" + order.getQuantity(), null
            );
        } else {
            order.setOrderStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
        }

        return order;
    }
}

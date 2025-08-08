package com.example.kafkacasestudy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafkacasestudy.entity.Order;
import com.example.kafkacasestudy.service.OrderProducer;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public String sendOrder(@RequestBody Order order) {
        orderProducer.sendOrder(order);
        return "Order sent to Kafka!";
    }
}
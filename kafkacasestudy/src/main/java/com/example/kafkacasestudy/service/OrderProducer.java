package com.example.kafkacasestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafkacasestudy.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderProducer {
    @Autowired
    private  KafkaTemplate<String, Order> kafkaTemplate;
    
    @Autowired
    private  ObjectMapper objectMapper;

    public void sendOrder(Order order) {
        try {
            String json = objectMapper.writeValueAsString(order);
            System.out.println("Sending order to Kafka: " + json);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting order to JSON: " + e.getMessage());
        }

        kafkaTemplate.send("order_topic", order);
    }
}

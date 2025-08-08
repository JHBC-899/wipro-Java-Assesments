package com.example.kafkacasestudy.service;

import com.example.kafkacasestudy.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
	@Autowired
    private  ObjectMapper objectMapper;

    @KafkaListener(topics = "order_topic", groupId = "order_group")
    public void consume(Order order) {
        try {
            String json = objectMapper.writeValueAsString(order);
            System.out.println("Received order from Kafka: " + json);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting received order to JSON: " + e.getMessage());
        }
    }
}

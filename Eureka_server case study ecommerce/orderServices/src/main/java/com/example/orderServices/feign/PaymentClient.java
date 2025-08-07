package com.example.orderServices.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.orderServices.dto.PaymentRequest;
import com.example.orderServices.dto.PaymentResponse;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping("/payments")
    PaymentResponse makePayment(@RequestBody PaymentRequest paymentRequest);
}
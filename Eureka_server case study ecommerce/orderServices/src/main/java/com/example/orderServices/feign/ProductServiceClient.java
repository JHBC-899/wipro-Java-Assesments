package com.example.orderServices.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.orderServices.dto.ProductResponse;

@FeignClient(name = "product-service", url = "http://localhost:8081") 
public interface ProductServiceClient {

    @GetMapping("/products/{id}")
    ProductResponse getProduct(@PathVariable("id") Integer id);

    @PutMapping("/products/{id}/reduceStock")
    void reduceStock(@PathVariable("id") Integer id, @RequestParam("qty") int qty);
}

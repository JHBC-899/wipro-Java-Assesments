package com.example.productServices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productServices.Repository.ProductRepository;
import com.example.productServices.entity.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    @PutMapping("/{id}/reduceStock")
    public Product reduceStock(@PathVariable Integer id, @RequestParam int qty) {
        Product p = repo.findById(id).orElse(null);
        if (p != null && p.getStock() >= qty) {
            p.setStock(p.getStock() - qty);
            repo.save(p);
        }
        return p;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        return repo.save(product);
    }
}

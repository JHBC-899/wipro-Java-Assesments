package com.example.productServices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productServices.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}

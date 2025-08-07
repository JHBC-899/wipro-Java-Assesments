package com.example.orderServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderServices.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}

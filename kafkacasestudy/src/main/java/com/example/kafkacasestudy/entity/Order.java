package com.example.kafkacasestudy.entity;

import java.time.LocalDate;

public class Order {
	private int orderId;
	private String customerName;
	private String productName;
	private int quantity;
	private double price;
	private LocalDate orderDate;
	
	public Order() {}

	public Order(int orderId, String customerName, String productName, int quantity, double price,
			LocalDate orderDate) {
		this.orderId = orderId;
		this.customerName = customerName;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	

}

package com.example.paymentservice.dto;

public class PaymentRequest {
    private Integer orderId;
    private double amount;
    private String paymentMethod;
    
	public PaymentRequest(Integer orderId, double amount, String paymentMethod) {
		this.orderId = orderId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
    
}

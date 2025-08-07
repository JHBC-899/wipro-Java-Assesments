package com.example.paymentservice.dto;

import com.example.paymentservice.entity.PaymentStatus;

public class PaymentResponse {
	private Integer orderId;
    private double amount;
    private PaymentStatus status;
    
    public PaymentResponse() {}
    
	public PaymentResponse(Integer orderId, double amount, PaymentStatus status) {
		this.orderId = orderId;
		this.amount = amount;
		this.status = status;
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
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
    

}

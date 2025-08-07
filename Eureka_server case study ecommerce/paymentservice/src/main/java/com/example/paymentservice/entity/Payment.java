package com.example.paymentservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="payments")
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	 private Integer orderId;
	    private double amount;
	    private String paymentMethod;

	    @Enumerated(EnumType.STRING)
	    private PaymentStatus status;

	    private LocalDateTime paymentDate;
	    
	    public Payment(){}

		public Payment(Integer id, Integer orderId, double amount, String paymentMethod, PaymentStatus status,
				LocalDateTime paymentDate) {
			this.id = id;
			this.orderId = orderId;
			this.amount = amount;
			this.paymentMethod = paymentMethod;
			this.status = status;
			this.paymentDate = paymentDate;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
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

		public PaymentStatus getStatus() {
			return status;
		}

		public void setStatus(PaymentStatus status) {
			this.status = status;
		}

		public LocalDateTime getPaymentDate() {
			return paymentDate;
		}

		public void setPaymentDate(LocalDateTime paymentDate) {
			this.paymentDate = paymentDate;
		}
		public static Payment create(Integer orderId, double amount, String paymentMethod,
                  PaymentStatus status, LocalDateTime paymentDate) {
return new Payment(null,orderId, amount, paymentMethod, status, paymentDate);
}
		
	    

}

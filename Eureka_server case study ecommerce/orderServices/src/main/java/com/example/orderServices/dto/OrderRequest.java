package com.example.orderServices.dto;

public class OrderRequest {
	private Integer productId;
	private Integer quantity;
	private String paymentMethod;

	
	  public Integer getProductId() {
		  return productId; 
	}
	    public void setProductId(Integer productId) { 
	    	this.productId = productId; 
	  }

	    public Integer getQuantity() { 
	    	return quantity; 
	    }
	    public void setQuantity(Integer quantity) { 
	    	this.quantity = quantity; 
	    	}
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
	
	
	

}

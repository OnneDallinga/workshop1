package com.rsvier.workshop1.model;

public class OrderLineItem {

	private int orderLineItemId;
	private int orderId;
	private int productId;
	private int quantity;
	
	public OrderLineItem() {
	}
	
	public OrderLineItem(int orderLineItemId,
						 int orderId,
						 int productId,
						 int quantity) {
		this.orderLineItemId = orderLineItemId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public int getOrderLineItemId() {
		return orderLineItemId;
	}
	
	public void setOrderLineItemId(int orderLineItemId) {
		this.orderLineItemId = orderLineItemId;
	}

	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

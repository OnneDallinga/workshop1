package com.rsvier.workshop1.model;

public class OrderLineItem {

	private Order parentOrder;
	private Product product;
	private int productQuantity;
	
	public OrderLineItem() {
	}
	
	public OrderLineItem(Order order,
						 Product product,
						 int productQuantity) {
		this.parentOrder = order;
		this.product = product;
		this.productQuantity = productQuantity;
	}
	
	public Order getParentOrder() {
		return parentOrder;
	}
	
	public void setParentOrder(Order order) {
		this.parentOrder = order;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getProductQuantity() {
		return productQuantity;
	}
	
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
}

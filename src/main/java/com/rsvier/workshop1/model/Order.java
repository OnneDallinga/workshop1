package com.rsvier.workshop1.model;

import java.math.BigDecimal;

public class Order {
	
	private int orderId;
	private Customer customer;
	private BigDecimal orderPriceTotal;
	private int orderItemsTotal;
	private boolean shipped;
	private Address shippedTo;
	private boolean completed;
	
	public Order() {
	}
	
	public Order(int orderId,
				 Customer customer,
				 BigDecimal orderPriceTotal,
				 int orderItemsTotal) {
		this.orderId = orderId;
		this.customer = customer;
		this.orderPriceTotal = orderPriceTotal;
		this.orderItemsTotal = orderItemsTotal;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public boolean isCompleted() {
		return this.completed;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public Customer getCustomerOfOrder() {
		return customer;
	}

	public void setCustomerOfOrder(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getOrderPriceTotal() {
		return orderPriceTotal;
	}

	public void setOrderPriceTotal(BigDecimal orderPriceTotal) {
		this.orderPriceTotal = orderPriceTotal;
	}

	public int getOrderItemsTotal() {
		return orderItemsTotal;
	}

	public void setOrderItemsTotal(int orderItemsTotal) {
		this.orderItemsTotal = orderItemsTotal;
	}

	public boolean isShipped() {
		return shipped;
	}

	public void setShipped(boolean shipped) {
		this.shipped = shipped;
	}

	public Address getShippedTo() {
		return shippedTo;
	}

	public void setShippedTo(Address shippedTo) {
		this.shippedTo = shippedTo;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customer.getCustomerId() + ", orderPriceTotal=" + orderPriceTotal +
				", orderItemsTotal=" + orderItemsTotal + ", shipped=" + shipped + ", shippedTo=" + shippedTo + "]";
	}	
}

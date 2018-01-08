package com.rsvier.workshop1.model;

import java.math.BigDecimal;

public class Order {
	
	private long orderId;
	private long customerId;
	private BigDecimal orderPriceTotal;
	private int orderItemsTotal;
	private boolean shipped;
	private Address shippedTo;
	
	public Order() {
	}
	
	public Order(long orderId,
				 long customerId,
				 BigDecimal orderPriceTotal,
				 int orderItemsTotal) {
		this.orderId = orderId;
		this.customerId = customerId;
		// TODO: Figure out BigDecimal as parameter //
		this.orderItemsTotal = orderItemsTotal;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", orderPriceTotal=" + orderPriceTotal +
				", orderItemsTotal=" + orderItemsTotal + ", shipped=" + shipped + ", shippedTo=" + shippedTo + "]";
	}	
}

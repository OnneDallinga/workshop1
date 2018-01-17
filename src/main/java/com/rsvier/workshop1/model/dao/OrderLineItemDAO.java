package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;

import com.rsvier.workshop1.model.OrderLineItem;

public interface OrderLineItemDAO {
	
	// Create
	public int createOrderLineItem(OrderLineItem orderLineItem);
	
	// Read
	public ArrayList<OrderLineItem> getAllOrderLineItemsByOrderId(int orderId);
	public OrderLineItem findOrderLineItemById(int orderLineItemId);
	
	// Update
	public void updateOrderLineItem(OrderLineItem orderLineItem);
	
	// Delete
	public void deleteOrderLineItem(OrderLineItem orderLineItem);

}

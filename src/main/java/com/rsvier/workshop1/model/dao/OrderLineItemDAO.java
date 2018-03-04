package com.rsvier.workshop1.model.dao;

import java.util.List;

import com.rsvier.workshop1.model.OrderLineItem;

public interface OrderLineItemDAO {
	
	// Create
	public boolean createOrderLineItem(OrderLineItem orderLineItem);
	
	// Read
	public List<OrderLineItem> getAllOrderLineItemsByOrderId(int orderId);
	public List<OrderLineItem> getAllOrderLineItemsForProductId (int productId);
	
	// Update
	public void updateOrderLineItem(OrderLineItem orderLineItem);
	
	// Delete
	public void deleteSingleOrderLineItem(OrderLineItem orderLineItem);
	public void deleteAllLineItemsFromOrder(int orderId);
}

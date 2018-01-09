package com.rsvier.workshop1.model;

import java.util.ArrayList;

public interface OrderDAO {
	
	// Create
	public void newOrder(Order order);
	
	// Read
	public ArrayList<Order> findAllOrders();
	public Order findOrder(long orderId);
	
	// Update
	public void updateOrder(Order order);
	
	// Delete
	public void deleteSingleOrder(long orderId);
	public void deleteAllOrdersCustomer(long customerId);

}

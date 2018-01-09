package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Order;

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

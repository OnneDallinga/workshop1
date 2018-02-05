package com.rsvier.workshop1.model.dao;

import java.util.List;

import com.rsvier.workshop1.model.Order;

public interface OrderDAO {
	
	// Create
	public int createOrder(Order order);
	
	// Read
	public List<Order> findAllOrders();
	public Order findOrderById(int orderId);
	
	// Update
	public void updateOrder(Order order);
	
	// Delete
	public void deleteOrder(Order order);

}

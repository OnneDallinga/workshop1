package com.rsvier.workshop1.model.dao;

import java.util.List;

import com.rsvier.workshop1.model.Order;
import com.rsvier.workshop1.model.Customer;

public interface OrderDAO {
	
	// Create
	public int createOrder(Order order, Customer customer);
	
	// Read
	public Order findOrderById(int orderId);
	public List<Order> findAllOrders();
	public List<Order> findCompletedOrders();
	public List<Order> findPendingOrders();
	public boolean isOrderStoredWithId(int orderId);
	
	// Update
	public boolean updateOrder(Order order, Customer customer);
	
	// Delete
	public boolean deleteOrder(Order order);

}

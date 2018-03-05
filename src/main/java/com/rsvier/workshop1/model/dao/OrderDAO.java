package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.rsvier.workshop1.model.Order;
import com.rsvier.workshop1.model.Customer;

public interface OrderDAO {
	
	// Create
	public int createOrder(Order order, Customer customer);
	
	// Read
	public List<Order> findAllOrders();
	public Order findOrderById(int orderId);
	public ArrayList<Order> findCompletedOrdersOfCustomer(Customer customer);
	public ArrayList<Order> findPendingOrdersOfCustomer(Customer customer);
	public boolean isOrderStoredWithId(int orderId);
	
	// Update
	public boolean updateOrder(Order order, Customer customer);
	
	// Delete
	public boolean deleteOrder(Order order);

}

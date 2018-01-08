package com.rsvier.workshop1.model;

import java.util.List;

public interface OrderDAO {
	
	public List<Order> getAllOrders();
	public Order getOrder(long orderId);
	public void updateOrder(Order order);
	public void deleteOrder(Order order);

}

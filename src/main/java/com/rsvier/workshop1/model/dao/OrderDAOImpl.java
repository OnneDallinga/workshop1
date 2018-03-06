
package com.rsvier.workshop1.model.dao;

import com.rsvier.workshop1.model.Order;
import com.rsvier.workshop1.model.Customer;
import com.rsvier.workshop1.database.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderDAOImpl implements OrderDAO {

	private String query;
	private Logger logger = Logger.getLogger(OrderDAOImpl.class.getName());

	@Override
	public int createOrder(Order order, Customer customer) {
		int newOrderId = 0;
		query = "INSERT INTO `` (total_price, total_products, shipped_status, customerID) VALUES (?,?,?,?);";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setBigDecimal(1, order.getOrderPriceTotal());
			stmt.setInt(2, order.getOrderItemsTotal());
			stmt.setBoolean(3,  order.isShipped());
			stmt.setInt(4, customer.getCustomerId());
			stmt.executeUpdate();
			try (ResultSet rs = stmt.getGeneratedKeys();){
				if (rs.next()) {
					newOrderId = rs.getInt(1);
					order.setOrderId(newOrderId);
					logger.info("Succesfully created new order.");
				}
			} catch (SQLException e) {
				System.out.println("Creating new order failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return newOrderId;
	}
	
	@Override
	public Order findOrderById(int orderId) {
		Order foundOrder = new Order();
		Customer customer = new Customer();
		query = "SELECT * FROM order WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			stmt.setInt(1, orderId);
			try (ResultSet rs = stmt.executeQuery();) {
				if(rs.next()) {
					foundOrder.setOrderId(rs.getInt(1));
					foundOrder.setOrderPriceTotal(rs.getBigDecimal(2));
					foundOrder.setOrderItemsTotal(rs.getInt(3));
					foundOrder.setShipped(rs.getBoolean(4));
					// Assigns a customer to the order by its id
					customer.setCustomerId(rs.getInt(5));
					foundOrder.setCustomerOfOrder(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundOrder;
	}

	@Override
	public List<Order> findAllOrders() {
		List<Order> list = new ArrayList<Order>();
		Customer customer = new Customer();
		query = "SELECT * FROM order;";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			try (ResultSet rs = stmt.executeQuery();) {
				while(rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt(1));
					order.setOrderPriceTotal(rs.getBigDecimal(2));
					order.setOrderItemsTotal(rs.getInt(3));
					order.setShipped(rs.getBoolean(4));
					// Assigns a new customer to the order by its id
					customer.setCustomerId(rs.getInt(5));
					order.setCustomerOfOrder(customer);
					list.add(order);
				}
				logger.info("Total orders:" + rs.getRow());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			logger.info("Could not retrieve orders.");
			e.printStackTrace();
		} 
		return list;
	}
	
	// TODO: Fix
	@Override
	public List<Order> findCompletedOrders() {
		List<Order> listOfCompletedOrders = new ArrayList<>();
		query = "SELECT * FROM order WHERE customerID = (?)";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			stmt.setObject(1, customer.getCustomerId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Order foundOrder = new Order();
				foundOrder.setOrderId(rs.getInt(1));
				foundOrder.setOrderPriceTotal(rs.getBigDecimal(2));
				foundOrder.setOrderItemsTotal(rs.getInt(3));
				foundOrder.setShipped(rs.getBoolean(4));
				foundOrder.setCustomerOfOrder(customer);
				foundOrder.setCompleted(rs.getBoolean(6));
				logger.info("Found 1 order");
				if (foundOrder.isCompleted()) {
					logger.info("Order is complete. Added to list.");
					listOfCompletedOrders.add(foundOrder);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfCompletedOrders;
	}

	// TODO Fix
	@Override
	public List<Order> findPendingOrders() {
		List<Order> listOfPendingOrders = new ArrayList<>();
		query = "SELECT * FROM order WHERE customerID = (?)";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			stmt.setInt(1, customer.getCustomerId());	    
			ResultSet rs = stmt.executeQuery();
			logger.info("Connected to database.");
			while(rs.next()) {
				Order foundOrder = new Order();
				foundOrder.setOrderId(rs.getInt(1));
				foundOrder.setOrderPriceTotal(rs.getBigDecimal(2));
				foundOrder.setOrderItemsTotal(rs.getInt(3));
				foundOrder.setShipped(rs.getBoolean(4));
				foundOrder.setCustomerOfOrder(customer);
				foundOrder.setCompleted(rs.getBoolean(6));
				logger.info("Found an order!");
				if (!foundOrder.isCompleted()) {
					logger.info("Order is pending. Added to list.");
					listOfPendingOrders.add(foundOrder);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfPendingOrders;
	}
	
	@Override
	public boolean isOrderStoredWithId(int orderId) {
		boolean isStored = false;
		query = "SELECT * FROM order WHERE id=?";
		query = "SELECT * FROM product WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");	    
			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				isStored = true;
			else
				isStored = false;
		} catch (Exception e) {
			logger.info("No order found.");
			e.printStackTrace();
		}
		return isStored;
	}

	@Override
	public boolean updateOrder(Order order, Customer customer) {
		query = "UPDATE order SET total_price = ?, total_products = ?," +
				"shipped_status = ?, customerID = ? WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			stmt.setBigDecimal(1, order.getOrderPriceTotal());
			stmt.setInt(2, order.getOrderItemsTotal());
			stmt.setBoolean(3, order.isShipped());
			stmt.setInt(4, customer.getCustomerId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.info("Updating order failed.");
			e.printStackTrace();
			return false;
		} 
		return true;
	}

	@Override
	public boolean deleteOrder(Order order) {
		query = "DELETE * FROM order WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			stmt.setInt(1, order.getOrderId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.info("Deleting order failed.");
			e.printStackTrace();
			return false;
		} 
		return true;
	}
}

package com.rsvier.workshop1.model.dao;

import com.rsvier.workshop1.model.OrderLineItem;
import com.rsvier.workshop1.database.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderLineItemDAOImpl implements OrderLineItemDAO {

	private String query;	
	private Logger logger = Logger.getLogger(OrderDAOImpl.class.getName());

	@Override
	public int createOrderLineItem(OrderLineItem orderLineItem) {
		int newOrderLineItemId = 0;
		query = "INSERT INTO order_line_item (productID, orderID, quantity) VALUES (?,?,?);";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = stmt.getGeneratedKeys();) {
			logger.info("Connected to database.");
			stmt.setInt(2, orderLineItem.getProductId());
			stmt.setInt(3, orderLineItem.getOrderId());
			stmt.setInt(4, orderLineItem.getQuantity());
			stmt.executeUpdate();
			try {
				if (rs.next()) {
					newOrderLineItemId = rs.getInt(1);
					orderLineItem.setOrderLineItemId(newOrderLineItemId);
					logger.info("Succesfully added new line item.");
				}           
			} catch (SQLException e) {
				logger.info("Adding new line item failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return newOrderLineItemId;
	}

	@Override
	public List<OrderLineItem> getAllOrderLineItemsByOrderId(int orderId) {
		List<OrderLineItem> list = new ArrayList<OrderLineItem>();
		query = "SELECT * FROM order_line_item WHERE orderID=?;";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");	      
			while(rs.next()) {
				OrderLineItem orderLineItem = new OrderLineItem();
				orderLineItem.setOrderLineItemId(rs.getInt(1));
				orderLineItem.setProductId(rs.getInt(2));
				orderLineItem.setOrderId(rs.getInt(3));
				orderLineItem.setQuantity(rs.getInt(4));
				list.add(orderLineItem);
			}
			logger.info("Total line items:" + rs.getRow());
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public OrderLineItem findOrderLineItemById(int orderLineItemId) {
		OrderLineItem foundOrderLineItem = new OrderLineItem();
		query = "SELECT * FROM order_line_item WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");
			stmt.setObject(1, orderLineItemId);
			if(rs.next()) {
				foundOrderLineItem.setOrderLineItemId(rs.getInt(1));
				foundOrderLineItem.setProductId(rs.getInt(2));
				foundOrderLineItem.setOrderId(rs.getInt(3));
				foundOrderLineItem.setQuantity(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return foundOrderLineItem;
	}

	@Override
	public void updateOrderLineItem(OrderLineItem orderLineItem) {
		query = "UPDATE orderLineItem SET productID = ?, orderID = ?," +
				"quantity = ? WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			stmt.setInt(1, orderLineItem.getProductId());
			stmt.setInt(2, orderLineItem.getOrderId());
			stmt.setInt(3, orderLineItem.getQuantity());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void deleteOrderLineItem(OrderLineItem orderLineItem) {
		query = "DELETE FROM order_line_item WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			stmt.setInt(1, orderLineItem.getOrderLineItemId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}

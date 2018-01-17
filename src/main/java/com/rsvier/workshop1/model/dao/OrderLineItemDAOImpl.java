package com.rsvier.workshop1.model.dao;

import java.sql.*;
import com.rsvier.workshop1.model.OrderLineItem;
import java.util.ArrayList;
import com.rsvier.workshop1.database.DataSource;

public class OrderLineItemDAOImpl implements OrderLineItemDAO {

	private Connection connection;
	private String query;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	@Override
	public int createOrderLineItem(OrderLineItem orderLineItem) {
		int newOrderLineItemId = 0;
		statement = null;
		query = "INSERT INTO order_line_item (productID, orderID, quantity) VALUES (?,?,?);";
		try {
			connection = DataSource.getConnection();
		    statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    statement.setInt(2, orderLineItem.getProductId());
		    statement.setInt(3, orderLineItem.getOrderId());
		    statement.setInt(4, orderLineItem.getQuantity());
		    statement.executeUpdate();
		    try {
		      resultSet = statement.getGeneratedKeys();
		      if (resultSet.next()) {
		    	newOrderLineItemId = resultSet.getInt(1);
		    	orderLineItem.setOrderLineItemId(newOrderLineItemId);
		      }           
		    } catch (SQLException e) {
		      System.out.println("Creating new user failed.");
		    }
		 } catch (SQLException e) {
		      e.printStackTrace();
		 } finally {
		   try {
		    connection.close();
		    } catch (SQLException e) {
		    }
		 }
		    return newOrderLineItemId;
	}

	@Override
	public ArrayList<OrderLineItem> getAllOrderLineItemsByOrderId(int orderId) {
		statement = null;
		ArrayList<OrderLineItem> list = new ArrayList<OrderLineItem>();
		query = "SELECT * FROM order_line_item WHERE orderID=?;";
		try {
		  connection = DataSource.getConnection();
		  statement = connection.prepareStatement(query);
		  resultSet = statement.executeQuery();
			      
		  while(resultSet.next()) {
			OrderLineItem orderLineItem = new OrderLineItem();
			orderLineItem.setOrderLineItemId(resultSet.getInt(1));
			orderLineItem.setProductId(resultSet.getInt(2));
			orderLineItem.setOrderId(resultSet.getInt(3));
			orderLineItem.setQuantity(resultSet.getInt(4));
			list.add(orderLineItem);
		  }
		  resultSet.close();
		} catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	    	connection.close();
		  } catch (SQLException e) {
		  }
	    }
		return list;
	}

	@Override
	public OrderLineItem findOrderLineItemById(int orderLineItemId) {
		statement = null;
		OrderLineItem foundOrderLineItem = new OrderLineItem();
		query = "SELECT * FROM order_line_item WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setObject(1, orderLineItemId);
	      resultSet = statement.executeQuery();
	      
	      if(resultSet.next()) {
			foundOrderLineItem.setOrderLineItemId(resultSet.getInt(1));
			foundOrderLineItem.setProductId(resultSet.getInt(2));
			foundOrderLineItem.setOrderId(resultSet.getInt(3));
			foundOrderLineItem.setQuantity(resultSet.getInt(4));
		  }
		} catch (SQLException e) {
		  e.printStackTrace();
		} finally {
		  try {
		    connection.close();
		  } catch (SQLException e) {
		  }
		}
	    return foundOrderLineItem;
	}

	@Override
	public void updateOrderLineItem(OrderLineItem orderLineItem) {
		statement = null;
	    query = "UPDATE orderLineItem SET productID = ?, orderID = ?," +
	    		"quantity = ? WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setInt(1, orderLineItem.getProductId());
	      statement.setInt(2, orderLineItem.getOrderId());
	      statement.setInt(3, orderLineItem.getQuantity());
	      statement.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        connection.close();
	      } catch (SQLException e) {
	      }
	    }
	}

	@Override
	public void deleteOrderLineItem(OrderLineItem orderLineItem) {
		statement = null;
	    query = "DELETE FROM order_line_item WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setInt(1, orderLineItem.getOrderLineItemId());
	      statement.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        connection.close();
	      } catch (SQLException e) {
	      }
	    }
	}
}

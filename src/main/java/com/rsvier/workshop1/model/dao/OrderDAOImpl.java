package com.rsvier.workshop1.model.dao;

import java.sql.*;
import java.util.ArrayList;
import com.rsvier.workshop1.database.DataSource;
import com.rsvier.workshop1.model.Order;

public class OrderDAOImpl implements OrderDAO {
	
	private Connection connection;
	private String query;
	private PreparedStatement statement;
	private ResultSet resultSet;

	@Override
	public int createOrder(Order order) {
		int newOrderId = 0;
		statement = null;
		query = "INSERT INTO order (total_price, total_products, shipped_status, customerID) VALUES (?,?,?,?);";
		try {
			connection = DataSource.getConnection();
		    statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    statement.setBigDecimal(1, order.getOrderPriceTotal());
		    statement.setInt(2, order.getOrderItemsTotal());
		    statement.setBoolean(3,  order.isShipped());
		    statement.setInt(4, order.getCustomerId());
		    statement.executeUpdate();
		    try {
		      resultSet = statement.getGeneratedKeys();
		      if (resultSet.next()) {
		    	  newOrderId = resultSet.getInt(1);
		    	  order.setOrderId(newOrderId);
		      }           
		    } catch (SQLException e) {
		      System.out.println("Creating new order failed.");
		    }
		 } catch (SQLException e) {
		      e.printStackTrace();
		 } finally {
		   try {
		    connection.close();
		    } catch (SQLException e) {
		    }
		 }
		    return newOrderId;
	}

	@Override
	public ArrayList<Order> findAllOrders() {
		statement = null;
		ArrayList<Order> list = new ArrayList<Order>();
		query = "SELECT * FROM order;";
		try {
		  connection = DataSource.getConnection();
		  statement = connection.prepareStatement(query);
		  resultSet = statement.executeQuery();
			      
		  while(resultSet.next()) {
		    Order order = new Order();
		    order.setOrderId(resultSet.getInt(1));
		    order.setOrderPriceTotal(resultSet.getBigDecimal(2));
		    order.setOrderItemsTotal(resultSet.getInt(3));
		    order.setShipped(resultSet.getBoolean(4));
		    order.setCustomerId(resultSet.getInt(5));
			list.add(order);
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
	public Order findOrderById(int orderId) {
		statement = null;
		Order foundOrder = new Order();
		query = "SELECT * FROM order WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setObject(1, orderId);
	      resultSet = statement.executeQuery();
	      
	      if(resultSet.next()) {
	    	foundOrder.setOrderId(resultSet.getInt(1));
	    	foundOrder.setOrderPriceTotal(resultSet.getBigDecimal(2));
	    	foundOrder.setOrderItemsTotal(resultSet.getInt(3));
	    	foundOrder.setShipped(resultSet.getBoolean(4));
	    	foundOrder.setCustomerId(resultSet.getInt(5));
		  }
		} catch (SQLException e) {
		  e.printStackTrace();
		} finally {
		  try {
		    connection.close();
		  } catch (SQLException e) {
		  }
		}
	    return foundOrder;
	}

	@Override
	public void updateOrder(Order order) {
		statement = null;
	    query = "UPDATE order SET total_price = ?, total_products = ?," +
	        "shipped_status = ?, customerID = ? WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setBigDecimal(1, order.getOrderPriceTotal());
	      statement.setInt(2, order.getOrderItemsTotal());
	      statement.setBoolean(3, order.isShipped());
	      statement.setInt(4, order.getCustomerId());
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
	public void deleteOrder(Order order) {
		statement = null;
	    query = "SELECT * FROM order WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setInt(1, order.getOrderId());
	      resultSet = statement.executeQuery();
	      if (!resultSet.next()) {
	        query = "DELETE FROM order WHERE id=?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, order.getOrderId());
	        statement.executeUpdate();
	      }
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

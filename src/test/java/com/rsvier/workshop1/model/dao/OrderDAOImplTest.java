package com.rsvier.workshop1.model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rsvier.workshop1.database.DataSource;
import com.rsvier.workshop1.model.Order;

class OrderDAOImplTest {
	
	OrderDAOImpl orderDao = new OrderDAOImpl();
	Logger logger = Logger.getLogger(OrderDAOImplTest.class.getName());

	@BeforeEach
	void setUp() {
		logger.info("Setting up database..");
		try {
			Connection conn = DataSource.getConnection();
			// Set up two order rows for testing
			String query = "INSERT INTO order (total_price, total_products, shipped_status, customerID)" +
						   "VALUES (47.0, 9, 0, 12)";
			Statement stmt = conn.createStatement();
			stmt.addBatch(query);
			String query2 = "INSERT INTO order (total_price, total_products, shipped_status, customerID)" +
							"VALUES (412.4, 61, 1, 1)";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			fail("Error instantiating database table: " + ex.getMessage());
		}
	}

	@AfterEach
	void tearDown() {
		logger.info("Cleaning up database..");
		try {
			Connection conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM order";
			stmt.addBatch(query);
			String query2 = "ALTER TABLE order AUTO_INCREMENT = 1";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	// FIX - returns 0
	void testCreateOrder() {
		Order order = new Order(11284, 281, new BigDecimal("76.4"), 3);

		// The method returns the created order's id which should be 11284 given setUp()
		int createdOrderId = orderDao.createOrder(order);

		assertEquals(11284, createdOrderId);
	}

	@Test
	// FIX - returns 0
	void testFindAllOrders() {
		List<Order> orders = orderDao.findAllOrders();

		assertAll(
		() -> assertEquals(2, orders.size()),
		() -> assertNotNull(orders.get(0).getOrderItemsTotal()),
		() -> assertNotNull(orders.get(1).getOrderPriceTotal())
		);
	}

	@Test
	// FIX - returns 0
	void testFindOrderById() {
		Order order = orderDao.findOrderById(1);
		
		assertEquals (1, order.getOrderId());
	}

	@Test
	void testUpdateOrder() {
		// Goal change shipped status from false to true for first order in DB
		Order orderToUpdate = new Order();
		orderToUpdate.setOrderId(1);
		orderToUpdate.setShipped(true);
		
		orderDao.updateOrder(orderToUpdate);
		
		try {
			Connection conn = DataSource.getConnection();
			String query = "SELECT * FROM customer WHERE id=1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			assertEquals(true, rs.getBoolean(4));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}	
	}

	@Test
	// Throws an exception due to the logic of deleting order with ID 2 and then trying to retrieve it from DB
	void testDeleteOrder() throws Exception {
		Order order = new Order();
		order.setOrderId(2);

		orderDao.deleteOrder(order);

		try {
			Connection conn = DataSource.getConnection();
			String query = "SELECT * FROM order WHERE id=2";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}

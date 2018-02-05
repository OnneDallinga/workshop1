package com.rsvier.workshop1.model.dao;

import static org.junit.jupiter.api.Assertions.*;

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
import com.rsvier.workshop1.model.Customer;

class CustomerDAOImplTest {
	
	CustomerDAOImpl customerDao = new CustomerDAOImpl();
	Logger logger = Logger.getLogger(CustomerDAOImplTest.class.getName());

	@BeforeEach
	void setUp() throws Exception {
		logger.info("Setting up database..");
		try {
			Connection conn = DataSource.getConnection();
			// Set up two product rows for testing
			String query = "INSERT INTO customer (first_name, last_name, last_name_preposition, email, phone_number)" +
						   "VALUES ('Ilja', 'Gort', ' ', 'wijnboertje51@hetnet.nl', '0628997288')";
			Statement stmt = conn.createStatement();
			stmt.addBatch(query);
			String query2 = "INSERT INTO customer (first_name, last_name, last_name_preposition, email, phone_number)" +
					   "VALUES ('Pim-Andre', 'Bergen en Henegouwen', 'van', 'cool_KiDD8@hotmail.com', '0206247745')";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			fail("Error instantiating database table: " + ex.getMessage());
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		logger.info("Cleaning up database..");
		try {
			Connection conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM customer";
			stmt.addBatch(query);
			String query2 = "ALTER TABLE customer AUTO_INCREMENT = 1";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void canCreateCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("Jan");
		customer.setLastName("Timmerman");

		// The method returns the created customer's id which should be 3 given setUp()
		int addedCustomerId = customerDao.createCustomer(customer);

		assertEquals(3, addedCustomerId);
	}

	@Test
	void canFindAllCustomers() {
		List<Customer> customers = customerDao.findAllCustomers();

		assertAll(
		() -> assertEquals(2, customers.size()),
		() -> assertNotNull(customers.get(0).getFirstName()),
		() -> assertNotNull(customers.get(1).getFirstName())
		);
	}

	@Test
	void canFindCustomerById() {
		Customer customer = customerDao.findCustomerById(1);

		assertEquals(1, customer.getCustomerId());
	}

	@Test
	void canFindCustomerByLastName() {
		Customer customer = customerDao.findCustomerByLastName("Gort");

		assertEquals("Gort", customer.getLastName());
	}

	@Test
	void canUpdateCustomer() {
		// Goal: change last name of 1st customer "Gort" to "Gort-Droog"
		Customer customerToUpdate = new Customer();
		customerToUpdate.setCustomerId(1);
		customerToUpdate.setLastName("Gort-Droog");
		
		customerDao.updateCustomer(customerToUpdate);
		
		try {
			Connection conn = DataSource.getConnection();
			String query = "SELECT * FROM customer WHERE id=1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			assertEquals("Gort-Droog", rs.getString(2));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void canDeleteCustomer() throws Exception {
		Customer customer = new Customer();
		customer.setCustomerId(2);

		customerDao.deleteCustomer(customer);

		try {
			Connection conn = DataSource.getConnection();
			String query = "SELECT * FROM customer WHERE id=2";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}

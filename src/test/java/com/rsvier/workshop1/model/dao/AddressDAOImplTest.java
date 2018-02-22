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
import com.rsvier.workshop1.model.Address;
import com.rsvier.workshop1.model.Customer;

class AddressDAOImplTest {
	
	AddressDAOImpl addressDao = new AddressDAOImpl();
	Logger logger = Logger.getLogger(AddressDAOImplTest.class.getName());

	@BeforeEach
	void setUp() {
		logger.info("Setting up database..");
		try {
			Connection conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO customer (id) VALUES (49, 221)";
			stmt.addBatch(query);
			// Set up two address rows for testing
			String query2 = "INSERT INTO address (postal_code, street_name, city, house_number, house_number_addition," +
					"address_type, customer_id) VALUES ('7006SE','Kleine Langeweg', 'Kortrijk', 44, 'E', 'Delivery', 49)";	
			stmt.addBatch(query2);
			String query3 = "INSERT INTO address (postal_code, street_name, city, house_number, house_number_addition," +
					"address_type, customer_id) VALUES ('2280ZZ','Mississippistraat','Oldenzaal', 2,' ','Delivery', 221)";
			stmt.addBatch(query3);
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
			String query = "DELETE FROM address";
			stmt.addBatch(query);
			String query2 = "ALTER TABLE address AUTO_INCREMENT = 1";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void testCreateAddress() {
		Address address = new Address();
		address.setPostalCode("4764");
		address.setHouseNumber(4);
		address.setStreet("Burgemeester Bitterbalplein");
		address.setCity("Rotterdam");
		
		Customer customer = new Customer();
		customer.setCustomerId(14);

		// The method returns the created address' id which should be 3 given setUp()
		int addedAddressId = addressDao.createAddress(address, customer);

		assertEquals(3, addedAddressId);
	}

	@Test
	void testFindAllAddresses() {
		List<Address> addresses = addressDao.findAllAddresses();

		assertAll(
		() -> assertEquals(2, addresses.size()),
		() -> assertNotNull(addresses.get(0).getPostalCode()),
		() -> assertNotNull(addresses.get(1).getHouseNumber())
		);
	}

	@Test
	void testFindAddressById() {
		Address address = addressDao.findAddressById(1);

		assertEquals(1, address.getAddressId());
	}
	
	@Test
	void testFindAddressByCustomer() {
		Customer customer = new Customer();
		customer.setCustomerId(49);
		
		List<Address> addresses = addressDao.findAddressesByCustomer(customer);
		
		assertAll(
		() -> assertEquals(1, addresses.size()),
		() -> assertNotNull(addresses.get(0).getPostalCode())
		);
	}

	@Test
	void testUpdateAddress() {
		// Goal: change house number of first address from 44 to 45
		Address addressToUpdate = new Address();
		addressToUpdate.setAddressId(1);
		addressToUpdate.setHouseNumber(45);
		
		addressDao.updateAddress(addressToUpdate);		
		
		try {
			Connection conn = DataSource.getConnection();
			String query = "SELECT * FROM address WHERE id=1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			assertEquals("45", rs.getString(2));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	void testDeleteAddress() {
		Address address = new Address();
		address.setAddressId(2);

		addressDao.deleteAddress(address);

		try {
			Connection conn = DataSource.getConnection();
			String query = "SELECT * FROM address WHERE id=2";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}

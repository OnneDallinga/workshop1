package com.rsvier.workshop1.model.dao;

import com.rsvier.workshop1.model.Customer;
import com.rsvier.workshop1.database.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;


public class CustomerDAOImpl implements CustomerDAO {

	private String query;
	private Logger logger = Logger.getLogger(CustomerDAOImpl.class.getName());

	@Override
	public int createCustomer(Customer customer) {
		int newCustomerId = 0;
		query = "INSERT INTO customer (first_name, last_name, last_name_preposition, email, phone_number)" +
				"VALUES (?,?,?,?,?);";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = stmt.getGeneratedKeys();) {
			logger.info("Connected to database.");
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getLastNamePreposition());
			stmt.setString(4, customer.getEmail());
			stmt.setString(5, customer.getPhoneNumber());
			stmt.executeUpdate();
			try {
				if (rs.next()) {
					newCustomerId = rs.getInt(1);
					customer.setCustomerId(newCustomerId);
					logger.info("Succesfully added new customer.");
				}           
			} catch (SQLException e) {
				logger.info("Creating new user failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return newCustomerId;
	}

	@Override
	public ArrayList<Customer> findAllCustomers() {
		ArrayList<Customer> list = new ArrayList<Customer>();
		query = "SELECT * FROM customer;";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");	      
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setLastNamePreposition(rs.getString(4));
				customer.setEmail(rs.getString(5));
				customer.setPhoneNumber(rs.getString(6));
				list.add(customer);
			}
			logger.info("Total customers:" + rs.getRow());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Customer findCustomerById(int customerId) {
		Customer foundCustomer = new Customer();
		query = "SELECT * FROM customer WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");
			stmt.setObject(1, customerId);	      
			if(rs.next()) {
				foundCustomer.setCustomerId(rs.getInt(1));
				foundCustomer.setFirstName(rs.getString(2));
				foundCustomer.setLastName(rs.getString(3));
				foundCustomer.setLastNamePreposition(rs.getString(4));
				foundCustomer.setEmail(rs.getString(5));
				foundCustomer.setPhoneNumber(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return foundCustomer;
	}

	@Override
	public Customer findCustomerByFirstName(String firstName) {
		Customer foundCustomer = new Customer();
		query = "SELECT * FROM customer WHERE first_name=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");
			stmt.setString(1, firstName);
			if(rs.next()) {
				foundCustomer.setCustomerId(rs.getInt(1));
				foundCustomer.setFirstName(rs.getString(2));
				foundCustomer.setLastName(rs.getString(3));
				foundCustomer.setLastNamePreposition(rs.getString(4));
				foundCustomer.setEmail(rs.getString(5));
				foundCustomer.setPhoneNumber(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundCustomer;
	}

	@Override
	public Customer findCustomerByLastName(String lastName) {
		Customer foundCustomer = new Customer();
		query = "SELECT * FROM customer WHERE last_name=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");
			stmt.setString(1, lastName);
			if(rs.next()) {
				foundCustomer.setCustomerId(rs.getInt(1));
				foundCustomer.setFirstName(rs.getString(2));
				foundCustomer.setLastName(rs.getString(3));
				foundCustomer.setLastNamePreposition(rs.getString(4));
				foundCustomer.setEmail(rs.getString(5));
				foundCustomer.setPhoneNumber(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return foundCustomer;
	}

	@Override
	public void updateCustomer(Customer customer) {
		query = "UPDATE customer SET first_name = ?, last_name = ?, last_name_preposition = ?, " +
				"email = ?, phone_number = ? WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getLastNamePreposition());
			stmt.setString(4, customer.getEmail());
			stmt.setString(5, customer.getPhoneNumber());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/* Full customer deletion includes deletion of all orders with the associated customer */
	@Override
	public void deleteCustomer(Customer customer) {
		query = "SELECT * FROM order WHERE customer_customerID=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");
			stmt.setInt(1, customer.getCustomerId());
			if (!rs.next()) {
				query = "DELETE FROM customer WHERE id=?";
				try (PreparedStatement stmt2 = conn.prepareStatement(query);) {
					stmt2.setInt(1, customer.getCustomerId());
					stmt2.executeUpdate();
				}
			} else {
				logger.info("Client has active orders in place, cannot delete.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}

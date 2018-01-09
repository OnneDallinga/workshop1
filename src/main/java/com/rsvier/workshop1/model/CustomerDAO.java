package com.rsvier.workshop1.model;

import java.util.ArrayList;

public interface CustomerDAO {
	
	// Create
	public void saveCustomer(Customer customer);
	
	// Read
	public ArrayList<Customer> findAllCustomers();
	public Customer findCustomer(long customerId);
	
	// Update
	public void updateCustomer(Customer customer);
	
	// Delete
	// Based on first and last name
	public void deleteCustomer(Customer customer);
	// Based on customer ID
	public void deleteCustomer(long customerId);


}

package com.rsvier.workshop1.model.dao;

import java.util.List;

import com.rsvier.workshop1.model.Customer;

public interface CustomerDAO {
	
	// Create
	public int createCustomer(Customer customer);
	
	// Read
	public List<Customer> findAllCustomers();
	public Customer findCustomerById(int customerId);
	public Customer findCustomerByLastName(String lastName);
	
	// Update
	public void updateCustomer(Customer customer);
	
	// Delete
	public void deleteCustomer(Customer customer);

}

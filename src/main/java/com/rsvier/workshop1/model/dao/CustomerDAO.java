package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Customer;

public interface CustomerDAO {
	
	// Create
	public int createCustomer(Customer customer);
	
	// Read
	public ArrayList<Customer> findAllCustomers();
	public Customer findCustomerById(int customerId);
	public Customer findCustomerByFirstName(String firstName);
	public Customer findCustomerByLastName(String lastName);
	
	// Update
	public void updateCustomer(Customer customer);
	
	// Delete
	public void deleteCustomer(Customer customer);

}

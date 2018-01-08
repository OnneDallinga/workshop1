package com.rsvier.workshop1.model;

import java.util.List;

public interface CustomerDAO {
	
	public List<Customer> getAllCustomers();
	public Customer getCustomer(long customerId);
	public void updateCustomer(Customer customer);
	public void deleteCustomer(Customer customer);

}

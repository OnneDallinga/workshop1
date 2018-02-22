package com.rsvier.workshop1.model.dao;

import java.util.List;

import com.rsvier.workshop1.model.Address;
import com.rsvier.workshop1.model.Customer;

public interface AddressDAO {

	// Create
	public int createAddress(Address address, Customer customer);
	
	// Read
	public List<Address> findAllAddresses();
	public Address findAddressById(int addressId);
	public List<Address> findAddressesByCustomer(Customer customer);
		
	// Update
	public void updateAddress (Address address);
		
	// Delete
	public void deleteAddress(Address address);
}

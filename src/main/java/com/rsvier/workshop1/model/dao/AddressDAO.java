package com.rsvier.workshop1.model.dao;

import java.util.List;

import com.rsvier.workshop1.model.Address;

public interface AddressDAO {

	// Create
	public int createAddress(Address address);
	
	// Read
	public List<Address> findAllAddresses();
	public Address findAddress(int addressId);
		
	// Update
	public void updateAddress (Address address);
		
	// Delete
	public void deleteAddress(Address address);
}

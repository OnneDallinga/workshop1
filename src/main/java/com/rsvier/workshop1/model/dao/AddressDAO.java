package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Address;

public interface AddressDAO {

	// Create
	public void createAddress(Address address);
	
	// Read
	public ArrayList<Address> findAllAddresses();
	public Address findAddress(long addressId);
		
	// Update
	public void updateAddress (long addressId);
		
	// Delete
	public void deleteAddress(long addressId);
}

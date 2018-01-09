package com.rsvier.workshop1.model;

import java.util.ArrayList;

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

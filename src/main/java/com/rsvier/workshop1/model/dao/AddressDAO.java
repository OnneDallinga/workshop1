package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Address;

public interface AddressDAO {

	// Create
	public int createAddress(Address address);
	
	// Read
	public ArrayList<Address> findAllAddresses();
	public Address findAddress(int addressId);
		
	// Update
	public void updateAddress (Address address);
		
	// Delete
	public void deleteAddress(Address address);
}

package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Customer;

public interface AccountDAO {
	
	// Read
	public boolean login(String username, String password);
	public int getUserID(String username);
	public boolean isAdmin(int userID);
	public ArrayList<String> getUsernameList();
	public ArrayList<String> retrieveAccountProperties();
	
	// Create
	public boolean createAccount(Customer newCustomer);
	
	// Modify
	public boolean changeUsername();
	public boolean changePassword();
	
	// Delete
	public boolean deleteAccount(String deleteThisUser);
	
}

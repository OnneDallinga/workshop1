package com.rsvier.workshop1.model.dao;

public interface AccountDAO {
	
	// Read
	public boolean login();
	
	// Create
	public boolean createAccount();
	
	// Modify
	public boolean changeUsername();
	public boolean changePassword();
	
	// Delete
	public boolean deleteAccount();
	
}

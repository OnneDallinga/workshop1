package com.rsvier.workshop1.model.dao;

public interface AccountDAO {
	
	// Read
	public boolean login(String username, String password);
	public int getUserID(String username);
	public boolean isAdmin(int userID);
	
	// Create
	public boolean createAccount();
	
	// Modify
	public boolean changeUsername();
	public boolean changePassword();
	
	// Delete
	public boolean deleteAccount();
	
}

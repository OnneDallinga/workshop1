package com.rsvier.workshop1.useraccounts;

public class User {
	private boolean admin = true;
	private String username;
	private int userID;
	
	protected User (String username, int userID, boolean admin) {
		this.username = username;
		this.userID = userID;
		this.admin = admin;
	}
	
	public String getUsername() {
		return username;
	}
	public boolean isAdmin() {
		return admin;
	}
	public int getUserID() {
		return userID;
	}
}
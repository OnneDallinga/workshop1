package com.rsvier.workshop1.useraccounts;

public class User {
	private boolean admin = true;
	private String username;
	
	public User (String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	public boolean isAdmin() {
		return admin;
	}
}

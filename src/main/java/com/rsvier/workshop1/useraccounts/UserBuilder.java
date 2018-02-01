package com.rsvier.workshop1.useraccounts;

import com.rsvier.workshop1.model.dao.*;

public class UserBuilder {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String lastNamePreposition;
	private String email;
	private String phoneNumber;
	private int id;
	private boolean isAdmin;
	
	public User build() {
		id = new AccountDAOImpl().getUserID(username);
		isAdmin = new AccountDAOImpl().isAdmin(id);
		return new User(username, id, isAdmin);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setLastNamePreposition(String lastNamePreposition) {
		this.lastNamePreposition = lastNamePreposition;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
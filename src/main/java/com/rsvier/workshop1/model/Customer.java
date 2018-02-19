package com.rsvier.workshop1.model;

public class Customer {

	private int customerId;
	private String firstName;
	private String lastName;
	private String lastNamePreposition;
	private String email;
	private String phoneNumber;
	private boolean customerActive;
	private String username;
	private String encryptedPassword;
	private String userType;
	private int active;
	private byte[] salt;

	public Customer() {
	}

	// Constructor with basic params
	public Customer(int customerId,
			String firstName,
			String lastName,
			String lastNamePreposition
			) {

		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastNamePreposition = lastNamePreposition;
	}

	// Complete constructor with all params
	public Customer(int customerId,
			String firstName,
			String lastName,
			String lastNamePreposition,
			String email,
			String phoneNumber) {

		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastNamePreposition = lastNamePreposition;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastNamePreposition() {
		return lastNamePreposition;
	}

	public void setLastNamePreposition(String lastNamePreposition) {
		this.lastNamePreposition = lastNamePreposition;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEncryptedPassword () {
		return this.encryptedPassword;
	}
	
	public void setEncryptedPassword (String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	public String getUsername () {
		return this.username;
	}
	
	public void setUsername (String username) {
		this.username = username;
	}
	
	public String getUserType () {
		return this.userType;
	}
	
	public void setUserType (String userType) {
		this.userType = userType;
	}
	
	public boolean isCustomerActive() {
		return customerActive;
	}

	public void setCustomerActive(boolean customerActive) {
		this.customerActive = customerActive;
	}
	
	public void setActive (int active) {
		this.active = active;
	}
	
	public int getActive () {
		return this.active;
	}
	
	public void setSalt (byte[] salt) {
		this.salt = salt;
	}
	
	public byte[] getSalt () {
		return this.salt;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", lastNamePreposition=" + lastNamePreposition + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", customerActive=" + customerActive + "]";
	}

	/* Customers are equal when customerID matches */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerId != other.customerId)
			return false;
		return true;
	}


}

package model;

public class Customer {
	
	private long customerId;
	private String firstName;
	private String lastName;
	private String lastNamePreposition;
	private String email;
	private String phoneNumber;
	private boolean customerActive;
	
	public Customer() {
	}
	
	// Constructor with basic params
	public Customer(long customerId,
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
	public Customer(long customerId,
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

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
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

	public boolean isCustomerActive() {
		return customerActive;
	}

	public void setCustomerActive(boolean customerActive) {
		this.customerActive = customerActive;
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

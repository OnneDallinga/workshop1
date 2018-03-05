package com.rsvier.workshop1.model;

public class Address {

	private int addressId;
	private Customer customer;
	private String street;
	private int houseNumber;
	private String houseNumberAddition;
	private String postalCode;
	private String city;
	private String addressType;
	private boolean active;
	
	public Address() {
	}
	
	/* Constructor with parameters that render an address' uniqueness */
	public Address(String postalCode,
				   int houseNumber,
				   String houseNumberAddition,
				   Customer customer) {
	}
	
	/* Constructor with all parameters */
	public Address(String street,
				   int houseNumber,
				   String houseNumberAddition,
				   String postalCode,
				   String city,
				   String addressType,
				   boolean active,
				   Customer customer) {
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public Customer getCustomerAtAddress() {
		return customer;
	}
	
	public void setCustomerAtAddress(Customer customer) {
		this.customer = customer;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getHouseNumberAddition() {
		return houseNumberAddition;
	}

	public void setHouseNumberAddition(String houseNumberAddition) {
		this.houseNumberAddition = houseNumberAddition;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", houseNumber=" + houseNumber
				+ ", houseNumberAddition=" + houseNumberAddition + ", postalCode=" + postalCode + ", city=" + city
				+ ", addressType=" + addressType + ", active=" + active + ", customerId =" + customer.getCustomerId();
	}

	/* Addresses are considered equal when postal code, house number and house number addition all match */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (houseNumber != other.houseNumber)
			return false;
		if (houseNumberAddition == null) {
			if (other.houseNumberAddition != null)
				return false;
		} else if (!houseNumberAddition.equals(other.houseNumberAddition))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		return true;
	}
}

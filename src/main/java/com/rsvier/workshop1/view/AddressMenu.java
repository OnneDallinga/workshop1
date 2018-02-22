package com.rsvier.workshop1.view;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Address;

public class AddressMenu extends View {
	
	@Override
	public void displayMessage() {
	}
	
	public void displayMenuHeader() {
		System.out.println("Address Menu \n");
	}
	
	public void displayMenuOptions() {
		System.out.print("1. List customer's address(es) \n" +
						 "2. Add new address \n" +
						 "3. Update address details \n" +
						 "4. Delete address \n" +
						 "8. Return to customer menu \n" +
						 "9. Return to main menu \n" +
						 "0. Exit program \n" +
						 "\n");
	}
	
	public void displayAddressDetailsHeader() {
		System.out.printf("%-3d %-7s %-4d %-3s %-15s %-10s %-4s \\n",
						   "id",
						   "psotal code",
						   "house #",
						   "add.",
						   "street",
						   "city",
						   "type");
	}
	
	public void displayAddressDetails(Address address) {
		System.out.printf("%-3d %-7s %-4d %-3s %-15s %-10s %-4s \\n",
						  address.getAddressId(),
						  address.getPostalCode(),
						  address.getHouseNumber(),
						  address.getHouseNumberAddition(),
						  address.getStreet(),
						  address.getCity(),
						  address.getAddressType());
	}
	
	public void displayAllAddresses(ArrayList<Address> allAddresses) {
		for (Address address : allAddresses) {
			displayAddressDetails(address);
		}
	}
	
	public void displayAddressUpdateMenu() {
		System.out.print("What would you like to update? \n" +
						 "\n" +
						 "1. Name \n" +
						 "2. Email \n" +
						 "3. Phone number" +
						 "4. Address(es)" +
						 "\n");
	}
	
	public void askWhichAddress() {
		System.out.print("Please provide an ID-number or postal code of an address.");
	}

}

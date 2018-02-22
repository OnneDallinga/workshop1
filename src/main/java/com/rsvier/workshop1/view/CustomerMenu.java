package com.rsvier.workshop1.view;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Customer;

public class CustomerMenu extends View {
	
	@Override
	public void displayMessage() {
	}
	
	public void displayMenuHeader() {
		System.out.println("Customer Menu \n");
	}
	
	public void displayMenuOptions() {
		System.out.print("1. View customer list \n" +
						 "2. Find a customer" +
						 "3. Add new customer \n" +
						 "4. Update customer details \n" +
						 "5. Delete customer \n" +
						 "9. Return to main menu \n" +
						 "0. Exit program \n" +
						 "\n");
	}
	
	public void displayCustomerDetailsHeader() {
		System.out.printf("%-3d %-10s %-5s %-10s %-15s %-10s \n",
						   "id",
						   "last name",
						   "preposition",
						   "first name",
						   "email",
						   "phone #");
	}
	
	public void displayCustomerDetails(Customer customer) {
		System.out.printf("%-3d %-10s %-5s %-10s %-15s %-10s \\n",
						  customer.getCustomerId(),
						  customer.getLastName(),
						  customer.getLastNamePreposition(),
						  customer.getFirstName(),
						  customer.getEmail(),
						  customer.getPhoneNumber());
	}
	
	public void displayAllCustomers(ArrayList<Customer> allCustomers) {
		for (Customer customer : allCustomers) {
			displayCustomerDetails(customer);
		}
	}
	
	public void displayCustomerUpdateMenu() {
		System.out.print("What would you like to update? \n" +
						 "\n" +
						 "1. Name \n" +
						 "2. Email \n" +
						 "3. Phone number" +
						 "4. Address(es)" +
						 "\n");
	}
	
	public void askWhichCustomer() {
		System.out.print("Please provide an ID-number or last name of a customer.");
	}

}

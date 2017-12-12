package com.rsvier.workshop1.view;

public class UserCreationView extends View {
	
	@Override
	public void DisplayMessage() {
		System.out.println("Welcome. Please enter your information below in order to create an account."
				+ " Press 0 at any point to exit.");
		selectUsername();
		selectPassword();
		selectOtherInfo();
		
	}
	private void selectUsername() {
		//TODO: Add code for username, send it to the database for checking and store when possible
	}
	private void selectPassword() {
		//TODO: Add more code.
	}
	private void selectOtherInfo() { //placeholder method. To be replaced later with more detailed methods.
		//TODO: Add more code.
	}
	
}

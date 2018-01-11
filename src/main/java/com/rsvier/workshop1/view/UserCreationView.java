package com.rsvier.workshop1.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserCreationView extends View {
	
	@Override
	public void displayMessage() {
		System.out.println("Welcome. Please enter your information below in order to create an account."
				+ " Press 0 at any point to exit.");
	}
	public void selectUsername() {
		System.out.println("Please enter your username: ");
	}
	public void selectPassword() {
		System.out.println("Please enter your password: ");
	}
	public void selectOtherInfo() { //placeholder method. To be replaced later with more detailed methods.
	}
}

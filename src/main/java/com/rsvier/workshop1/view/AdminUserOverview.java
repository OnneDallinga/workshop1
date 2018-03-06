package com.rsvier.workshop1.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.rsvier.workshop1.controller.Controller;

public class AdminUserOverview extends View {
	
	@Override
	public void displayMessage() {
		System.out.println("Welcome to the user overview screen. Press 1 to get a list of your current users."
				+ " Press 2 to delete a user. Press 3 to change the permissions of a user. Press 9 to return to the main menu. Press 0 to exit.");
	}
	
	@Override
	public void displayMenu() {
		displayMenuHeader();
		displayDivider();
		displayMenuOptions();
	}
	
	public void displayMenuHeader() {
		System.out.println("User overview \n");
	}
	
	public void displayMenuOptions() {
		System.out.print("1. View all current users \n" +
				 "2. Delete a user \n" +
				 "3. Change user permissions \n" +
				 "9. Return to the main menu \n" +
				 "0. Exit program \n" +
				 "\n");
	}

	public void printAllUsers(ArrayList<String> allOfTheUsers) {
		for (String username : allOfTheUsers) {
			System.out.println(username);
		}
	}
}

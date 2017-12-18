package com.rsvier.workshop1.view;

import java.util.ArrayList;

public class AdminUserOverview extends View {
	
	@Override
	public void displayMessage() {
		System.out.println("Welcome to the user overview screen. Press 1 to get a list of your current users."
				+ " Press 2 to return to the main menu. Press 0 to exit.");
	}

	public void printAllUsers(ArrayList<String> allOfTheUsers) {
		for (int i = 0; i < allOfTheUsers.size(); i++) {
			System.out.print(allOfTheUsers.get(i) + " "
			+ allOfTheUsers.get(++i) + " "
			+ allOfTheUsers.get(++i) + " "
			+ allOfTheUsers.get(++i) + " "
			+ allOfTheUsers.get(++i) + " ");
			System.out.println(" ");
		}
	}
}

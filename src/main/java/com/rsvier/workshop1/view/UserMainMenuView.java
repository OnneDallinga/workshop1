package com.rsvier.workshop1.view;

import com.rsvier.workshop1.useraccounts.User;

public class UserMainMenuView extends View {

	@Override
	public void displayMessage() {
		System.out.println("Welcome to the main menu. Press 1 to view your pending and completed orders. Press 2 to view the available products."
				+ "Press 3 to view or edit your account information. Press 0 to exit.");
	}
}

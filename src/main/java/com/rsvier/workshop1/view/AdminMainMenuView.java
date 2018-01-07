package com.rsvier.workshop1.view;

public class AdminMainMenuView extends View {

	@Override
	public void displayMessage() {
		System.out.println("Welcome to the main menu (for admins). Press 1 to view or edit the current users. "
				+ "Press 2 to view or edit your products. Press 3 to view the orders. Press 0 to exit.");
	}
}

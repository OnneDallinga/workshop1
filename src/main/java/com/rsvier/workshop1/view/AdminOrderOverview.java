package com.rsvier.workshop1.view;

public class AdminOrderOverview extends View {
	
	@Override
	public void displayMessage() {
		System.out.println("Welcome to the order overview. Press 1 to view all current pending orders. "
				+ "Press 2 to search for finished orders. Press 9 to return to the main menu. Press 0 to exit.");
	}

}

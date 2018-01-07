package com.rsvier.workshop1.view;

public class AdminProductOverview extends View {
	
	@Override
	public void displayMessage() {
		System.out.println("Welcome to the product overview page. Press 1 to view all your products. "
				+ "Press 2 to add a new product. Press 3 to edit a product. Press 9 to return to the main menu. Press 0 to exit.");
	}
	
}

package com.rsvier.workshop1.view;

public class AdminMainMenuView extends View {

	@Override
	public void displayMessage() {
	}
	
	@Override
	public void displayMenu() {
		displayMenuHeader();
		displayDivider();
		displayMenuOptions();
	}
	
	public void displayMenuHeader() {
		System.out.println("Administrator Menu \n");
	}
	
	public void displayMenuOptions() {
		System.out.print("1. Manage user accounts \n" +
						 "2. Manage customers \n" +
						 "3. Manage products \n"+
						 "4. Manage orders \n\n");
	}
}

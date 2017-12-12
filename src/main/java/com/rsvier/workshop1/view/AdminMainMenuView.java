package com.rsvier.workshop1.view;

import com.rsvier.workshop1.useraccounts.User;

public class AdminMainMenuView extends View {

	public AdminMainMenuView(User currentUser) {
		possibleMenuOptions.add(1);
		possibleMenuOptions.add(2);
		possibleMenuOptions.add(3);
		//menuOptions.put(1, new adminUserOverview());
		//menuOptions.put(2, new adminProductOverview());
		//menuOptions.put(3, new adminOrderOverview());
		user = currentUser;
	}
	
	@Override
	public void displayMessage() {
		System.out.println("Welcome to the main menu (for admins). Press 1 to view or edit the current users. "
				+ "Press 2 to view or edit your products. Press 3 to view the orders. Press 0 to exit.");
	}
	
	
}

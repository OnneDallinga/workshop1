package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.Model;
import com.rsvier.workshop1.view.View;

public class AdminOrderController extends Controller {
	
	public AdminOrderController(View theView) {
		this.currentMenu = theView;
	}

	@Override
	public void runView() {
		currentMenu.displayMessage();
		int userMenuChoice = currentMenu.asksUserForMenuChoice(menuOptions);
		if (userMenuChoice == 9) {
			nextController = menuOptions.get(9);
			nextController.setUser(user);
			return;
		}
	}

}

package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.Model;
import com.rsvier.workshop1.view.View;

public class OrderController extends Controller {
	
	public OrderController(View theView) {
		this.currentMenu = theView;
	}

	@Override
	public void runView() {
		currentMenu.displayMessage();
		int userMenuChoice = currentMenu.asksUserForMenuChoice(menuOptions);
		if (userMenuChoice == 9) {
			nextController = menuOptions.get(9);
			return;
		}
	}

}

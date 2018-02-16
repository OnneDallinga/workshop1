package com.rsvier.workshop1.controller;

import java.util.HashMap;

import com.rsvier.workshop1.model.Model;
import com.rsvier.workshop1.view.AdminMainMenuView;
import com.rsvier.workshop1.view.View;

public class AdminProductController extends Controller {
	
	public AdminProductController(View theView) {
		this.currentMenu = theView;
		menuOptions = new HashMap<>();
		menuOptions.put(9, new AdminMainMenuController(new AdminMainMenuView()));
	}

	@Override
	public void runView() {
		currentMenu.displayMessage();
		int userMenuChoice = currentMenu.asksUserForMenuChoice(menuOptions);
		if (userMenuChoice == 9) {
			nextController = menuOptions.get(9);
			return;
		}
		if (userMenuChoice == 1) {
			
		}
	}
}

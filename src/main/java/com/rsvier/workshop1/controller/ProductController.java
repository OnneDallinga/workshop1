package com.rsvier.workshop1.controller;

import java.util.HashMap;

import com.rsvier.workshop1.model.Model;
import com.rsvier.workshop1.view.AdminMainMenuView;
import com.rsvier.workshop1.view.View;

public class ProductController extends Controller {
	
	public ProductController(View theView, Model theModel) {
		this.currentMenu = theView;
		this.theModel = theModel;
		menuOptions = new HashMap<>();
		menuOptions.put(9, new MainMenuController(new AdminMainMenuView()));
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

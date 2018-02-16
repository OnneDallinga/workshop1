package com.rsvier.workshop1.controller;

import java.util.HashMap;

import com.rsvier.workshop1.view.*;

public class UserMainMenuController extends Controller {
	
	public UserMainMenuController(View currentMenu) {
		this.currentMenu = currentMenu;
	}

	@Override
	public void runView() {
		menuOptions = new HashMap<Integer, Controller>();
		menuOptions.put(1, new UserOrderController(new UserOrderOverview()));
		menuOptions.put(2, new UserProductController(new UserProductOverview()));
		menuOptions.put(3,  new UserAccountController(new UserAccountView()));
		
		currentMenu.displayMessage();
		nextController = menuOptions.get(currentMenu.asksUserForMenuChoice(menuOptions));
		nextController.setUser(user);
	}

}

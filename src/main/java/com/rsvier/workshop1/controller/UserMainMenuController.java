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
		menuOptions.put(1, new CustomerController(new CustomerView()));
		menuOptions.put(2, new ProductController(new ProductView()));
		menuOptions.put(3, new OrderController(new OrderView()));
		
		currentMenu.displayMenu();
		nextController = menuOptions.get(currentMenu.asksUserForMenuChoice(menuOptions));
		nextController.setUser(user);
	}
}

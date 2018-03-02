package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.view.*;
import java.util.*;

public class AdminMainMenuController extends Controller {
	
	public AdminMainMenuController(View currentMenu) {
		this.currentMenu = currentMenu;
	}

	@Override
	public void runView() {
		menuOptions = new HashMap<Integer, Controller>();
		menuOptions.put(1, new AdminUserOverviewController(new AdminUserOverview()));
		menuOptions.put(2, new CustomerController(new CustomerView()));
		menuOptions.put(3, new ProductController(new ProductView()));
		menuOptions.put(4, new OrderController(new OrderView()));
		
		currentMenu.displayMessage();
		nextController = menuOptions.get(currentMenu.asksUserForMenuChoice(menuOptions));
		nextController.setUser(user);
	}
}
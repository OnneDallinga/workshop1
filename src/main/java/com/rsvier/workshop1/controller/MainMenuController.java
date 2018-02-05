package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.view.*;
import java.util.*;

public class MainMenuController extends Controller {
	
	public MainMenuController(View currentMenu) {
		this.currentMenu = currentMenu;
	}

	@Override
	public void runView() {
		menuOptions = new HashMap<Integer, Controller>();
		menuOptions.put(1, new UserOverviewController(new AdminUserOverview()));
		menuOptions.put(2, new ProductController(new AdminProductOverview()));
		menuOptions.put(3, new OrderController(new AdminOrderOverview()));
		
		currentMenu.displayMessage();
		nextController = menuOptions.get(currentMenu.asksUserForMenuChoice(menuOptions));
	}
}
package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.view.*;

public class MainMenuController extends Controller {
	
	public MainMenuController(View currentMenu, Model currentModel) {
		this.currentMenu = currentMenu;
		this.theModel = currentModel;
		//menuOptions.put(1, new Controller(new UserOverview(), new RetrieveUserInfoModel()));
		//menuOptions.put(2, new Controller(new AdminProductOverview(), new ProductsInDatabaseModel()));
		//menuOptions.put(3, new Controller(new AdminOrderOverview(), new OrdersInDatabaseModel()));
	}

	@Override
	public void runView() {
		currentMenu.displayMessage();
		nextController = menuOptions.get(currentMenu.asksUserForMenuChoice());
	}
}
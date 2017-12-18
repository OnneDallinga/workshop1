package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.useraccounts.*;
import com.rsvier.workshop1.view.*;

public class LoginController extends Controller {

	public LoginController (LoginMenuView theView, RetrieveUserInfoModel theModel) {
		this.currentMenu = theView;
		this.theModel = theModel;
	}
	
	@Override
	public void runView() {
		currentMenu.displayMessage();
		String[] userInput = ((LoginMenuView) currentMenu).asksUserForLogin();
		String username = userInput[0];
		String password = userInput[1];
		while (true) {
			if (((RetrieveUserInfoModel) theModel).login(username, password)) break;
		}
		UserBuilder userBuilder = new UserBuilder();
		userBuilder.setUsername(username);
		user = userBuilder.build();
		if (user.isAdmin()) {
			nextController = new MainMenuController(new AdminMainMenuView(), null);
		}
		else {
			nextController = new MainMenuController(new UserMainMenuView(), null);
		}
	}
}
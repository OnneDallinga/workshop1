package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.model.dao.AccountDAOImpl;
import com.rsvier.workshop1.useraccounts.*;
import com.rsvier.workshop1.view.*;

public class LoginController extends Controller {

	public LoginController (LoginMenuView theView, RetrieveUserInfoModel theModel) {
		this.currentMenu = theView;
		this.theModel = theModel;
	}
	
	@Override
	public void runView() {
		String username;
		currentMenu.displayMessage();
		while (true) {
			String[] userInput = ((LoginMenuView) currentMenu).asksUserForLogin();
			if (userInput.length == 0) { //empty array only occurs when user enters a 1
				nextController = new AccountCreationController(new UserCreationView(), new SendInfoToDatabaseModel());
				return;
			}
			username = userInput[0];
			String password = userInput[1];
			if (new AccountDAOImpl().login(username, password)) break;
			System.out.println("Incorrect username of password. Please try again or press 0 to exit.");
		}
		UserBuilder userBuilder = new UserBuilder();
		userBuilder.setUsername(username);
		user = userBuilder.build();
		if (user.isAdmin()) {
			nextController = new MainMenuController(new AdminMainMenuView());
		}
		else {
			nextController = new MainMenuController(new UserMainMenuView());
		}
	}
}
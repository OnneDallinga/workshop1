package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.Model;
import com.rsvier.workshop1.model.RetrieveUserInfoModel;
import com.rsvier.workshop1.model.SendInfoToDatabaseModel;
import com.rsvier.workshop1.model.ValidationModel;
import com.rsvier.workshop1.view.LoginMenuView;
import com.rsvier.workshop1.view.UserCreationView;
import com.rsvier.workshop1.view.View;

public class AccountCreationController extends Controller {
	private ValidationModel validator;
	
	public AccountCreationController (UserCreationView theView, Model theModel) {
		this.currentMenu = theView;
		this.theModel = theModel;
		this.validator = new ValidationModel();
	}

	@Override
	public void runView() {
		currentMenu.displayMessage();
		String requestedUsername = "";
		boolean validUsername = false;
		while (!validUsername) {
			((UserCreationView) currentMenu).selectUsername();
			requestedUsername = currentMenu.askUserForInput();
			validUsername = validator.validateUsername(requestedUsername);
		}
		System.out.println("Username accepted. Username: " + requestedUsername);
		
		String requestedPassword = "";
		boolean validPassword = false;
		while (!validPassword) {
			((UserCreationView) currentMenu).selectPassword();
			requestedPassword = currentMenu.askUserForInput();
			validPassword = validator.validatePassword(requestedPassword);
		}
		System.out.println("Password accepted. Password: " + requestedPassword);
	}
}

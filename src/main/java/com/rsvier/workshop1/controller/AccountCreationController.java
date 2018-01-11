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
		boolean validUsername = false;
		while (validUsername) {
			currentMenu.displayMessage();
			((UserCreationView) currentMenu).selectUsername();
			String requestedUsername = currentMenu.askUserForInput();
			
		}
		
	}

}

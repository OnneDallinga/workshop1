package com.rsvier.workshop1.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.rsvier.workshop1.model.Model;
import com.rsvier.workshop1.model.RetrieveUserInfoModel;
import com.rsvier.workshop1.model.SendInfoToDatabaseModel;
import com.rsvier.workshop1.model.ValidationModel;
import com.rsvier.workshop1.useraccounts.UserBuilder;
import com.rsvier.workshop1.view.LoginMenuView;
import com.rsvier.workshop1.view.UserCreationView;
import com.rsvier.workshop1.view.View;

public class AccountCreationController extends Controller {
	
	public AccountCreationController (UserCreationView theView, Model theModel) {
		this.currentMenu = theView;
		this.theModel = theModel;
	}

	@Override
	public void runView() {
		currentMenu.displayMessage();
		nextController = new LoginController(new LoginMenuView(), new RetrieveUserInfoModel());
		accountCreator();
	}
	
	private void accountCreator() {
		ArrayList<String> necessaryCustomerInformation = new RetrieveUserInfoModel().retrieveAccountProperties();
		boolean accountCreated = false;
		ArrayList<String> newUser = new ArrayList<>();
		while (!accountCreated) {
			for (String customerProperty : necessaryCustomerInformation) {
				boolean validInput = false;
				String userInput = "";
				while (!validInput) {
					userInput = ((UserCreationView) currentMenu).askUserForInput(customerProperty);
					validInput = new ValidationModel(userInput).validateNewUser(customerProperty);
				}
				newUser.add(userInput);
			}
			accountCreated = new SendInfoToDatabaseModel().createNewUser(necessaryCustomerInformation, newUser);
		}
		System.out.println("Account created. Returning to login screen..");
	}
}

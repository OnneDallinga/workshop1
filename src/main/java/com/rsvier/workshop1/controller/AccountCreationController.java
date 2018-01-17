package com.rsvier.workshop1.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.rsvier.workshop1.model.Customer;
import com.rsvier.workshop1.model.Model;
import com.rsvier.workshop1.model.RetrieveUserInfoModel;
import com.rsvier.workshop1.model.SendInfoToDatabaseModel;
import com.rsvier.workshop1.model.ValidationModel;
import com.rsvier.workshop1.model.dao.CustomerDAOImpl;
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
		Customer newCustomer = new Customer();
		String username = "";
		String password = "";
		
		
		while (!accountCreated) {
			for (String customerProperty : necessaryCustomerInformation) {
				boolean validInput = false;
				String userInput = "";
				while (!validInput) {
					userInput = ((UserCreationView) currentMenu).askUserForInput(customerProperty);
					validInput = new ValidationModel(userInput).validateNewUser(customerProperty);
				}
				switch (customerProperty) {
				case "username": // to be added later
					username = userInput;
					break;
				case "password": // to be added later
					password = userInput;
					break;
				case "first_name":
					newCustomer.setFirstName(userInput);
					break;
				case "last_name":
					newCustomer.setLastName(userInput);
					break;
				case "last_name_preposition":
					newCustomer.setLastNamePreposition(userInput);
					break;
				case "email":
					newCustomer.setEmail(userInput);
					break;
				case "phone_number":
					newCustomer.setPhoneNumber(userInput);
					break;
				default:
					System.out.println("Unidentified customer property. Please check your database. Program is closing..");
					System.exit(0);
				}
			}
			int customerID = new CustomerDAOImpl().createCustomer(newCustomer);
			newCustomer.setCustomerId(customerID);
			//TODO: Push username, password & customerID to login database
		}
		System.out.println("Account created. Returning to login screen..");
	}
}

package com.rsvier.workshop1.controller;

import java.util.ArrayList;
import com.rsvier.workshop1.model.Customer;
import com.rsvier.workshop1.model.PasswordHasher;
import com.rsvier.workshop1.model.ValidationModel;
import com.rsvier.workshop1.model.dao.AccountDAOImpl;
import com.rsvier.workshop1.model.dao.CustomerDAOImpl;
import com.rsvier.workshop1.view.LoginMenuView;
import com.rsvier.workshop1.view.UserCreationView;

public class AccountCreationController extends Controller {
	
	public AccountCreationController (UserCreationView theView) {
		this.currentMenu = theView;
	}

	@Override
	public void runView() {
		currentMenu.displayMessage();
		nextController = new LoginController(new LoginMenuView());
		accountCreator();
	}
	
	private void accountCreator() {
		ArrayList<String> necessaryCustomerInformation = new AccountDAOImpl().retrieveAccountProperties();
		boolean accountCreated = false;
		Customer newCustomer = new Customer();
		
		while (!accountCreated) {

			for (String customerProperty : necessaryCustomerInformation) {
				boolean validInput = false;
				String userInput = "";
				if (customerProperty.equals("hash")) continue; // requires no action
				while (!validInput) {
					userInput = ((UserCreationView) currentMenu).askUserForInput(customerProperty);
					validInput = new ValidationModel(userInput).validateNewUser(customerProperty);
				}
				switch (customerProperty) {
				case "username":
					newCustomer.setUsername(userInput);
					break;
				case "password":
					PasswordHasher passwordHasher = new PasswordHasher();
					byte[] salt = passwordHasher.generateSalt();
					newCustomer.setEncryptedPassword(passwordHasher.makeSaltedPasswordHash(userInput, salt));
					newCustomer.setSalt(salt);
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
			accountCreated = new AccountDAOImpl().createAccount(newCustomer);
			//TODO: Push username, password & customerID to login database
		}
		System.out.println("Account created. Returning to login screen..");
	}
}

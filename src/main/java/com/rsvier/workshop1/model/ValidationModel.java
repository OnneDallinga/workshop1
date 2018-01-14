package com.rsvier.workshop1.model;

import java.util.ArrayList;

public class ValidationModel { // Validates possible user inputs.
	private String userInput;
	
	public ValidationModel(String userInput) {
		this.userInput = userInput;
	}
	
	public boolean validateUsername () { // all logic for the usernames
		if ((!(userInput.matches("[A-Za-z0-9_]+"))) && (userInput.length() > 3)) {
			System.out.println("Your username is not valid. It should be at least 4 characters long"
					+ " and contain only letters or numbers.");
			return false;
		}
		ArrayList<String> allExistingUsernames = new RetrieveUserInfoModel().showAllUsernames();
		for (String usernames : allExistingUsernames) {
			if (usernames.equals(userInput)) {
				System.out.println("This username is already taken. Please select another.");
				return false;
			}
		}
		return true;	
	}

	public boolean validatePassword() { // all logic for the passwords
		if (!(userInput.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))) {
			System.out.println("Your password is not allowed. It must contain at least one"
					+ " uppercase letter, one lowercase letter, one number and one special character."
					+ " It must also be at least 8 characters long.");
			return false;
		}
		return true;
	}

}

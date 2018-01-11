package com.rsvier.workshop1.model;

import java.util.ArrayList;

public class ValidationModel { // Validates possible user inputs.
	
	public boolean validateUsername (String requestedUsername) { // all logic for the usernames
		if ((!(requestedUsername.matches("[A-Za-z0-9_]+"))) && (requestedUsername.length() > 3)) {
			System.out.println("Your username is not valid. It should be at least 4 characters long and contain only letters or numbers.");
			return false;
		}
		ArrayList<String> allExistingUsernames = new RetrieveUserInfoModel().showAllUsernames();
		for (String usernames : allExistingUsernames) {
			if (usernames.equals(requestedUsername)) {
				System.out.println("This username is already taken. Please select another.");
				return false;
			}
		}
		return true;	
	}

	public boolean validatePassword(String requestedPassword) { // all logic for the passwords
		
		if (!(requestedPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"))) {
			System.out.println("Your password is not allowed. It must contain at least one uppercase letter, one lowercase letter, one number and one special character."
					+ " It must also be at least 8 characters long.");
			return false;
		}
		return true;
	}

}

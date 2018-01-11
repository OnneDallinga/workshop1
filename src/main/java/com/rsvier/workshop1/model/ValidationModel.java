package com.rsvier.workshop1.model;

import java.util.ArrayList;

public class ValidationModel { // Validates possible user inputs.
	private ArrayList<String> allExistingUsernames;
	
	public boolean validateUsername (String requestedUsername) { // all logic for the Usernames
		if (requestedUsername.equals("0")) {
			System.out.println("Closing program.. ");
			System.exit(0);
		}
		
		if (requestedUsername.length() < 4) {
			System.out.println("Your username is too short. It should be at least 5 characters.");
			return false;
		}
		if (requestedUsername.length() > 15) {
			System.out.println("Your username is too long. The maximum amount of characters allowed is 15.");
			return false;
		}
		allExistingUsernames = new RetrieveUserInfoModel().showAllUsernames();
		for (String usernames : allExistingUsernames) {
			if (usernames.equals(requestedUsername)) {
				System.out.println("This username is already taken. Please select another.");
				return false;
			}
		}
		
		
		
		
		return true;	
	}

}

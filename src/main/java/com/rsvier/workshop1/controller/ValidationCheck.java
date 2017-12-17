package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.ValidationCheckModel;

public class ValidationCheck {
	private int userID;
	private ValidationCheckModel validationCheckModel;
	
	public boolean validateUser(String username, String password) {
		validationCheckModel = new ValidationCheckModel();
		boolean validatedUser = validationCheckModel.validateUser(username, password);
		userID = validationCheckModel.getUserID();
		return validatedUser;
	}

	public int getUserID() {
		return userID;
	}
}

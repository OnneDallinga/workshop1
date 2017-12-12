package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.ValidationCheckModel;

public class ValidationCheck {
	public boolean validateUser(String username, String password) {
		boolean validatedUser = new ValidationCheckModel().validateUser(username, password);	// this will be expanded to the SQL-database for checking
		return validatedUser;
	}
}

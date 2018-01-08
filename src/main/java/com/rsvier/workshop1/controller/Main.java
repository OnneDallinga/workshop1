package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.useraccounts.*;
import com.rsvier.workshop1.view.*;

public class Main {

	public static void main (String args[])  {
		Controller currentController = new LoginController(new LoginMenuView(), new RetrieveUserInfoModel());
		currentController.runView();
		//user is now logged in		
		User user = currentController.getUser();
		currentController = currentController.getNextController();
		//sets up the next menu
		
		while(true) { //infinite loop until the user stops the program
			currentController.runView(); // run next menu
			currentController = currentController.getNextController(); // get next menu & model from the users choice
		}
	}
}
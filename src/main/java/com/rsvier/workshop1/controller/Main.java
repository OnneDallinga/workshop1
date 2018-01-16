package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.useraccounts.*;
import com.rsvier.workshop1.view.*;

public class Main {

	public static boolean hikariEnabled;
	public static boolean mongoEnabled;

	public static void main (String args[])  {
		System.out.println("Welcome. Enable Hikari Connection Pool?");
		hikariEnabled = new UserMainMenuView().asksUserYesOrNo();
		System.out.println("Use Mongo Database?");
		mongoEnabled = new UserMainMenuView().asksUserYesOrNo();
		
		Controller currentController = new LoginController(new LoginMenuView(), new RetrieveUserInfoModel());
		currentController.runView(); // run next menu
		
		while(true) { //infinite loop until the user stops the program
			currentController = currentController.getNextController(); // get next menu & model from the users choice
			currentController.runView(); // run next menu
		}
	}
}
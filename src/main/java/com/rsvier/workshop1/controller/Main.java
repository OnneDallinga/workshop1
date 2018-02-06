package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.dao.DatabaseBuilderDAOImpl;
import com.rsvier.workshop1.view.*;

public class Main {

	public static boolean hikariEnabled;
	public static boolean mongoEnabled;

	public static void main (String args[])  {
		//new RetrieveUserInfoModel().showTable();
		//System.exit(0);
		
		
		System.out.println("Welcome. Enable Hikari Connection Pool?");
		hikariEnabled = new UserMainMenuView().asksUserYesOrNo();
		System.out.println("Use Mongo Database?");
		mongoEnabled = new UserMainMenuView().asksUserYesOrNo();
		System.out.println("Initialize database?");
		if (new UserMainMenuView().asksUserYesOrNo()) {
			if (new DatabaseBuilderDAOImpl().initializeDatabase()) {
				System.out.println("Database initalized");
			}
			else {
				System.out.println("Failed to initalize database");
			}
		}
		
		Controller currentController = new LoginController(new LoginMenuView());
		currentController.runView(); // run next menu
		
		while(true) { //infinite loop until the user stops the program
			currentController = currentController.getNextController(); // get next menu & model from the users choice
			currentController.runView(); // run next menu
		}
	}
}
package com.rsvier.workshop1.controller;

import java.util.*;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.view.*;

public class UserOverviewController extends Controller{
	private RetrieveUserInfoModel theModel;
	private AdminUserOverview currentMenu;
	
	public UserOverviewController (AdminUserOverview theView, RetrieveUserInfoModel theModel) {
		this.currentMenu = theView;
		this.theModel = theModel;
		menuOptions = new HashMap<>();
		menuOptions.put(9, new MainMenuController(new AdminMainMenuView()));
		menuOptions.put(1, new MainMenuController(new AdminMainMenuView())); // 1 is a valid option but doesn't go anywhere
		menuOptions.put(2, new MainMenuController(new AdminMainMenuView())); // 2 is a valid option but doesn't go anywhere
	}

	@Override
	public void runView() {
		boolean userWantsToStay = true;
		ArrayList<String> allOfTheUsers = theModel.showAllUsers(); // load the users from the database
		while (userWantsToStay) {
			currentMenu.displayMessage();
			int userMenuChoice = currentMenu.asksUserForMenuChoice(menuOptions);
			switch (userMenuChoice) {
			case 1: // Admin wants to view all users
				currentMenu.printAllUsers(allOfTheUsers); // show all of the users
				break;
			case 2: // Admin wants to delete a user
				String deleteThisUser = currentMenu.asksUserForUserChoice(allOfTheUsers); // a
				break;
			case 9: // Return to main menu
				nextController = menuOptions.get(9);
				userWantsToStay = false;
				break;
			}
		}
	}
}

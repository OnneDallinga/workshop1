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
		menuOptions.put(1, null);
		menuOptions.put(2, null);
	}

	@Override
	public void runView() {
		boolean userWantsToStay = true;
		ArrayList<String> allOfTheUsernames = theModel.showAllUsernames(); // load the users from the database
		while (userWantsToStay) {
			currentMenu.displayMessage();
			int userMenuChoice = currentMenu.asksUserForMenuChoice(menuOptions);
			switch (userMenuChoice) {
			case 1: // Admin wants to view all users
				currentMenu.printAllUsers(allOfTheUsernames); // show all of the users
				break;
			case 2: // Admin wants to delete a user
				String deleteThisUser = currentMenu.asksUserForUserChoice(allOfTheUsernames); // asks Admin to select a username to delete
				if (deleteThisUser.equals("9")) break; // 9 = cancel delete
				System.out.println("Delete this user: " + deleteThisUser + "?");
				boolean yesOrNo = currentMenu.asksUserYesOrNo();
				if (yesOrNo) {
					boolean success = new SendInfoToDatabaseModel().deleteUserFromDatabase(deleteThisUser);
					if (success) {
						System.out.println("User successfully deleted");
					}
				}
				break;
			case 9: // Return to main menu
				nextController = menuOptions.get(9);
				userWantsToStay = false;
				break;
			}
		}
	}
}

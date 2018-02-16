package com.rsvier.workshop1.controller;

import java.util.*;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.model.dao.AccountDAOImpl;
import com.rsvier.workshop1.view.*;

public class AdminUserOverviewController extends Controller{
	private AdminUserOverview currentMenu;
	
	public AdminUserOverviewController (AdminUserOverview theView) {
		this.currentMenu = theView;
		menuOptions = new HashMap<>();
		menuOptions.put(9, new AdminMainMenuController(new AdminMainMenuView()));
		menuOptions.put(1, null);
		menuOptions.put(2, null);
		menuOptions.put(3, null);
	}

	@Override
	public void runView() {
		boolean userWantsToStay = true;
		ArrayList<String> allOfTheUsernames = new AccountDAOImpl().getUsernameList(); // load the users from the database
		while (userWantsToStay) {
			currentMenu.displayMessage();
			int userMenuChoice = currentMenu.asksUserForMenuChoice(menuOptions);
			switch (userMenuChoice) {
			case 1: // Admin wants to view all users
				currentMenu.printAllUsers(allOfTheUsernames); // show all of the users
				break;
			case 2: // Admin wants to delete a user
				String deleteThisUser = currentMenu.asksUserForUserChoice(allOfTheUsernames); // asks Admin to select a username to delete
				if (deleteThisUser.equals("9")) break; // 9 = cancel
				System.out.println("Delete this user: " + deleteThisUser + "?");
				boolean yesOrNo = currentMenu.asksUserYesOrNo();
				if (yesOrNo) {
					boolean success = new AccountDAOImpl().deleteAccount(deleteThisUser); // Deletes the user
					if (success) {
						System.out.println("User successfully deleted");
						allOfTheUsernames = new AccountDAOImpl().getUsernameList(); // Re-load the users from the database
					}
					else {
						System.out.println("Failed to delete user");
					}
				}
				break;
			case 3: // Admin wants to change the account privileges of a user
				String changeThisUser = currentMenu.asksUserForUserChoice(allOfTheUsernames);
				if (changeThisUser.equals("9")) break; // 9 = cancel
				int userID = new AccountDAOImpl().getUserID(changeThisUser);
				boolean isAdmin = new AccountDAOImpl().isAdmin(userID);
				if (isAdmin) {
					System.out.println("This user is already an admin");
					break;
				}
				System.out.println("Upgrade this user to an admin: " + changeThisUser + "?");
				yesOrNo = currentMenu.asksUserYesOrNo();
				if (yesOrNo) {
					boolean success = new AccountDAOImpl().upgradeAccount(changeThisUser); // Upgrades the user
					if (success) {
						System.out.println("User permission successfully changed");
						allOfTheUsernames = new AccountDAOImpl().getUsernameList(); // Re-load the users from the database
					}
					else {
						System.out.println("Failed to change the user.");
					}
				}
				break;
			case 9: // Return to main menu
				nextController = menuOptions.get(9);
				nextController.setUser(user);
				userWantsToStay = false;
				break;
			}
		}
	}
}

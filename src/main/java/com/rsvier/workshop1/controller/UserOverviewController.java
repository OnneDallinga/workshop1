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
	}

	@Override
	public void runView() {
		
		currentMenu.displayMessage();
		int userMenuChoice = currentMenu.asksUserForMenuChoice(menuOptions);
		if (userMenuChoice == 2) {
			nextController = menuOptions.get(2);
			return;
		}
		ArrayList<String> allOfTheUsers;
		allOfTheUsers = theModel.showAllUsers();
		currentMenu.printAllUsers(allOfTheUsers);
		runView();
	}
}

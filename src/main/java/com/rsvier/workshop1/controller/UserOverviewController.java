package com.rsvier.workshop1.controller;

import java.util.HashMap;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.view.*;

public class UserOverviewController extends Controller{
	private RetrieveUserInfoModel theModel;
	
	public UserOverviewController (View theView, RetrieveUserInfoModel theModel) {
		this.currentMenu = theView;
		this.theModel = theModel;

	}

	@Override
	public void runView() {
		menuOptions = new HashMap<>();
		menuOptions.put(2, new MainMenuController(new AdminMainMenuView()));
		
		currentMenu.displayMessage();
		int userMenuChoice = currentMenu.asksUserForMenuChoice();
		while (!(userMenuChoice == 2 || userMenuChoice == 1)) {
			System.out.println("Invalid input. Please try again");
			userMenuChoice = currentMenu.asksUserForMenuChoice();
		}
		if (userMenuChoice == 2) {
			nextController = menuOptions.get(2);
			return;
		}
		theModel.showAllUsers();
		
		
		
		
	}
}

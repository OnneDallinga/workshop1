package com.rsvier.workshop1.controller;

import java.io.IOException;

import com.rsvier.workshop1.useraccounts.User;
import com.rsvier.workshop1.view.*;

public class Main {

	
	public static void main (String args[]) throws IOException {		
		Controller controller = new Controller(new LoginMenuView());
		controller.runView();
		if (controller.getCurrentUser().isAdmin()) {
			controller = new Controller(new AdminMainMenuView(controller.getCurrentUser()));
			User user = controller.getCurrentUser();
		}
		else {
			controller = new Controller(new UserMainMenuView(controller.getCurrentUser()));
		}
		while (true) {
			controller.runView();
		}
	}
}

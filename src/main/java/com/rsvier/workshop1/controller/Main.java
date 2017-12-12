package com.rsvier.workshop1.controller;

import java.io.IOException;
import com.rsvier.workshop1.view.*;

public class Main {
	
	public static void main (String args[]) throws IOException {		
		Controller Controller = new Controller(new LoginMenuView());
		Controller.runView();
		if (Controller.getCurrentUser().isAdmin()) {
			Controller = new Controller(new AdminMainMenuView(Controller.getCurrentUser()));
		}
		else {
			Controller = new Controller(new UserMainMenuView(Controller.getCurrentUser()));
		}
		while (true) {
			Controller.runView();
			
		}
	}
}

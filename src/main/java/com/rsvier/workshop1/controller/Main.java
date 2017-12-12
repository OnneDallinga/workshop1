package com.rsvier.workshop1.controller;

import java.io.IOException;
import com.rsvier.workshop1.view.LoginMenuView;

public class Main {
	private int userMenuChoice;
	private boolean isAdmin = false;
	private String username;
	
	public static void main (String args[]) throws IOException {		
		Controller Controller = new Controller(new LoginMenuView());
		Controller.RunView();
	}
}

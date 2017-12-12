package com.rsvier.workshop1.controller;

import java.io.IOException;
import com.rsvier.workshop1.useraccounts.*;
import com.rsvier.workshop1.view.*;

public class Controller { // parent
	private View currentMenu;
	//private Model theModel; //add later
	
	public Controller (View currentMenu /*Model theModel*/) throws IOException {
		this.currentMenu = currentMenu;
		//this.theModel = theModel;
	}
	public void runView() {
		this.currentMenu.displayMessage();
	}
	public View getCurrentMenu() {
		return currentMenu;
	}
	public User getCurrentUser() {
		return currentMenu.getUser();
	}
}

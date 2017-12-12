package com.rsvier.workshop1.controller;

import java.io.IOException;
import com.rsvier.workshop1.view.*;

public class Controller { // parent
	private View currentMenu;
	//private Model theModel; //add later
	private int userMenuChoice;
	
	public Controller (View currentMenu /*Model theModel*/) throws IOException {
		this.currentMenu = currentMenu;
		//this.theModel = theModel;
	}
	public void RunView() {
		this.currentMenu.DisplayMessage();
	}
	public View getCurrentMenu() {
		return currentMenu;
	}
}

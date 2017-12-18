package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.useraccounts.*;
import com.rsvier.workshop1.view.*;
import java.util.*;
import com.rsvier.workshop1.controller.*;

public class MenuManager {
	private View nextMenu;
	private Model nextModel;
	private User user;
	
	private class MenuOptions {
		private HashMap<Integer,Controller> menuMap = new HashMap<>();
	}
	
	public Controller getNextController(View currentView, int userMenuChoice) {
		if (currentView instanceof AdminMainMenuView) {
			
		}

		return null;
	}
	
}

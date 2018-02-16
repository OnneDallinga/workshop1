package com.rsvier.workshop1.controller;

import com.rsvier.workshop1.model.*;
import com.rsvier.workshop1.useraccounts.*;
import com.rsvier.workshop1.view.*;
import java.util.*;

public abstract class Controller { // parent
	protected View currentMenu;
	protected User user;
	protected Controller nextController;
	protected HashMap<Integer,Controller> menuOptions;
	
	public abstract void runView();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Controller getNextController() {
		return nextController;
	}
}
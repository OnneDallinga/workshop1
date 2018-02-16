package com.rsvier.workshop1.controller;

import java.util.ArrayList;
import java.util.HashMap;

import com.rsvier.workshop1.model.Order;
import com.rsvier.workshop1.model.dao.OrderDAOImpl;
import com.rsvier.workshop1.view.*;

public class UserOrderController extends Controller {
	
	
	public UserOrderController(View currentMenu) {
		this.currentMenu = currentMenu;
	}

	@Override
	public void runView() { //Welcome to the order menu. Press 1 to view your pending orders. Press 2 to view your order history. Press 9 to return to the main menu. Press 0 to exit
		menuOptions = new HashMap<Integer, Controller>();
		menuOptions.put(1, null);
		menuOptions.put(2, null);
		menuOptions.put(9, null);
		
		currentMenu.displayMessage();
		
		switch (currentMenu.asksUserForMenuChoice(menuOptions)) {
		case 1:
			ArrayList<Order> pendingOrderList = new OrderDAOImpl().findPendingOrdersByCustomerId(user.getUserID());
			for (Order order : pendingOrderList) {
				System.out.println(order);
			}
			break;
		case 2:
			ArrayList<Order> completedOrderList = new OrderDAOImpl().findCompletedOrdersByCustomerId(user.getUserID());
			for (Order order : completedOrderList) {
				System.out.println(order);
			}
			break;
		case 9:
			nextController = new UserMainMenuController(new UserMainMenuView());
			nextController.setUser(user);
			break;
		}
	}
}

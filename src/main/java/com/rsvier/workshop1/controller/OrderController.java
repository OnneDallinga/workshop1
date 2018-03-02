package com.rsvier.workshop1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.rsvier.workshop1.model.dao.OrderDAO;
import com.rsvier.workshop1.model.dao.OrderDAOImpl;
import com.rsvier.workshop1.model.Order;
import com.rsvier.workshop1.model.Validator;
import com.rsvier.workshop1.view.AdminMainMenuView;
import com.rsvier.workshop1.view.OrderView;
import com.rsvier.workshop1.view.UserMainMenuView;

public class OrderController extends Controller {
	
	private OrderView currentMenu;
	private OrderDAO orderDao;
	private Scanner input = new Scanner(System.in);
	
	public OrderController(OrderView theView) {
		this.currentMenu = theView;
		orderDao = new OrderDAOImpl();
	}
	
	@Override
	public void runView() {
		currentMenu.displayMenu();
		int userMenuChoice = currentMenu.asksUserForMenuChoice(menuOptions);
		switch (userMenuChoice) {
		case 1: findAllOrders();
				break;
		case 2: findPendingOrdersOnly();
				break;
		case 3: findCompletedOrdersOnly();
				break;
		case 4: findOrder();
				break;
		case 5: addNewOrder();
				break;
		case 6: updateOrder();
				break;
		case 7: deleteOrder();
				break;
		case 9: // Returns to main menu
				if (user.isAdmin()) {
					nextController = new AdminMainMenuController(new AdminMainMenuView());
					nextController.setUser(user);
				}
				else {
					nextController = new UserMainMenuController(new UserMainMenuView());
					nextController.setUser(user);
				}
				break;
		default: System.out.println("Not a valid option.");
				currentMenu.displayMenu();
		}
	}

	public void findAllOrders() {
		ArrayList<Order> allOrders = (ArrayList<Order>) orderDao.findAllOrders();
		currentMenu.displayOrderPropertiesHeader();
		currentMenu.displayDivider();
		currentMenu.displayAllOrders(allOrders);
	}
	
	public void findOrder() {
		Order foundOrder = new Order();
		String findThisOrder = currentMenu.askUserForInput();
		if (Validator.isAnInt(findThisOrder)) {
			// TODO Don't like using a Try-Catch here, improve later
			try { // if user input was an int
				foundOrder = orderDao.findOrderById(Integer.parseInt(findThisOrder));
			} catch (Exception ex) {
				System.out.println("Could not find an order with that ID.");
			}
		} 
		currentMenu.displayOrderPropertiesHeader();
		currentMenu.displayDivider();
		currentMenu.displayOrderProperties(foundOrder);
	}
	
	public void findPendingOrdersOnly() {
		ArrayList<Order> pendingOrderList = new OrderDAOImpl().findPendingOrdersByCustomerId(user.getUserID());
		currentMenu.displayOrderPropertiesHeader();
		currentMenu.displayDivider();
		currentMenu.displayAllOrders(pendingOrderList);
	}
	
	public void findCompletedOrdersOnly() {
		ArrayList<Order> completedOrderList = new OrderDAOImpl().findCompletedOrdersByCustomerId(user.getUserID());
		currentMenu.displayOrderPropertiesHeader();
		currentMenu.displayDivider();
		currentMenu.displayAllOrders(completedOrderList);
	}
	
	public void addNewOrder() {
		//TODO: Write
	}
	
	public void updateOrder() {
		currentMenu.displayOrderUpdateMenu();

		String userChoice = input.nextLine(); 
		if(!Validator.validateMenuChoice(userChoice)) { // Necessary to return the menu if input was deemed invalid
			System.out.println("You did not enter a valid number. \n");
			currentMenu.displayOrderUpdateMenu();
		}
		
		//TODO Write cases
		int userChoiceNumber = Integer.parseInt(userChoice);
		switch(userChoiceNumber) {
			case 9:	currentMenu.displayMenu();
					break;
			default: System.out.println("Invalid choice. \n");
					currentMenu.displayOrderUpdateMenu();
		}
	}
	
	public void deleteOrder() {
		Order orderToDelete = new Order();
		
		int id = inputValidOrderId();
		
		orderToDelete.setOrderId(id);
		if(idIsInDatabase(id, orderDao)) {
			currentMenu.displayDeletionConfirmationPrompt(); // Require confirmation
			boolean yesOrNo = currentMenu.asksUserYesOrNo();
			if (yesOrNo) { // user answered yes
				orderDao.deleteOrder(orderToDelete);
				currentMenu.displayDeleteSuccess();
			} else {
				currentMenu.displayOperationCancelled();
			}
		} else {
			currentMenu.displayItemNotFound();
			currentMenu.pressEnterToReturn();
		}
	}
	
	public int inputValidOrderId() {
		currentMenu.promptUserForItemId();
		String attemptAtId = input.nextLine();
		while (!Validator.isAnInt(attemptAtId)) {
			System.out.println("You did not enter a valid order id.");
			attemptAtId = input.nextLine();
		}
		int id = Integer.parseInt(attemptAtId);
		return id;
	}
	
	public boolean idIsInDatabase(int id, OrderDAO orderDao) {
		if(orderDao.isOrderStoredWithId(id)) {
			return true;
		} else {
			return false;
		}
	}
}

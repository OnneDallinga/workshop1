package com.rsvier.workshop1.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.rsvier.workshop1.model.dao.AddressDAO;
import com.rsvier.workshop1.model.dao.CustomerDAO;
import com.rsvier.workshop1.model.dao.OrderDAO;
import com.rsvier.workshop1.model.dao.OrderDAOImpl;
import com.rsvier.workshop1.model.dao.OrderLineItemDAO;
import com.rsvier.workshop1.model.dao.OrderLineItemDAOImpl;
import com.rsvier.workshop1.model.dao.ProductDAO;
import com.rsvier.workshop1.model.Address;
import com.rsvier.workshop1.model.Customer;
import com.rsvier.workshop1.model.Order;
import com.rsvier.workshop1.model.OrderLineItem;
import com.rsvier.workshop1.model.Validator;
import com.rsvier.workshop1.view.AdminMainMenuView;
import com.rsvier.workshop1.view.OrderView;
import com.rsvier.workshop1.view.UserMainMenuView;

public class OrderController extends Controller {
	
	private OrderView currentMenu;
	private OrderDAO orderModel;
	private OrderLineItemDAO orderLineModel;
	private CustomerDAO customerModel;
	private AddressDAO addressModel;
	private ProductDAO productModel;
	private Scanner input = new Scanner(System.in);
	
	public OrderController(OrderView theView) {
		this.currentMenu = theView;
	}
	
	@Override
	public void runView() {
		currentMenu.displayMenu();
		int userMenuChoice = Integer.parseInt(currentMenu.askUserForInput());
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
		ArrayList<Order> allOrders = (ArrayList<Order>) orderModel.findAllOrders();
		currentMenu.displayOrderPropertiesHeader();
		currentMenu.displayDivider();
		currentMenu.displayAllOrders(allOrders);
	}
	
	public void findPendingOrdersOnly() {
		Customer customer = inputCustomer();
		ArrayList<Order> pendingOrderList = orderModel.findPendingOrders();
		currentMenu.displayOrderPropertiesHeader();
		currentMenu.displayDivider();
		currentMenu.displayAllOrders(pendingOrderList);
	}
	
	public void findCompletedOrdersOnly() {
		Customer customer = inputCustomer();
		ArrayList<Order> completedOrderList = orderModel.findCompletedOrders();
		currentMenu.displayOrderPropertiesHeader();
		currentMenu.displayDivider();
		currentMenu.displayAllOrders(completedOrderList);
	}
	
	public void findOrder() {
		Order foundOrder = new Order();
		String findThisOrder = currentMenu.askUserForInput();
		if (Validator.isAnInt(findThisOrder)) {
			// TODO Don't like using a Try-Catch here, improve later
			try { // if user input was an int
				foundOrder = orderModel.findOrderById(Integer.parseInt(findThisOrder));
			} catch (Exception ex) {
				System.out.println("Could not find an order with that ID.");
			}
		} 
		currentMenu.displayOrderPropertiesHeader();
		currentMenu.displayDivider();
		currentMenu.displayOrderProperties(foundOrder);
	}
	
	public void addNewOrder() {
		System.out.println("To enter a new order, you must associate an existing customer as a first step.");
		System.out.println("Do you wish to continue, or to cancel the operation?");
		
		boolean continueAdding = currentMenu.asksUserYesOrNo();
		
		if (!continueAdding) { // user wants to halt operation
			currentMenu.pressEnterToReturn();
			currentMenu.displayMenu();
		} else {	 // create the new order
			Order orderToAdd = new Order();

			Customer customer = inputCustomer();
			orderToAdd.setCustomerOfOrder(customer);

			boolean isShipped = inputShippingStatus();
			orderToAdd.setShipped(isShipped);

			Address shippedToAddress = inputShippedToAddressOfCustomer(customer);
			orderToAdd.setShippedTo(shippedToAddress);

			addLineItemToOrder();

			orderModel.createOrder(orderToAdd, customer);
			currentMenu.displayCreateSuccess();
		}
	}
	
	public void updateOrder() {
		currentMenu.displayOrderUpdateMenu();

		String userChoice = input.nextLine(); 
		if(!Validator.validateMenuChoice(userChoice)) { // Necessary to return the menu if input was deemed invalid
			System.out.println("You did not enter a valid number. \n");
			currentMenu.displayOrderUpdateMenu();
		}
		
		int userChoiceNumber = Integer.parseInt(userChoice);
		switch(userChoiceNumber) {
			case 1: editLineItemOfOrder();
					break;
			case 2: addLineItemToOrder();
					break;
			case 3: removeLineItemFromOrder();
					break;
			case 4: editShippingStatusOfOrder();
					break;
			case 5: editCustomerOfOrder();
					break;
			case 6: editAddressOfOrder();
					break;
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
		if(idIsInDatabase(id)) {
			currentMenu.displayDeletionConfirmationPrompt(); // Require confirmation
			boolean yesOrNo = currentMenu.asksUserYesOrNo();
			if (yesOrNo) { // user answered yes
				orderModel.deleteOrder(orderToDelete);
				// Also deletes all associated line items of the order
				orderLineModel.deleteAllLineItemsFromOrder(orderToDelete.getOrderId());	
				currentMenu.displayDeleteSuccess();
			} else {
				currentMenu.displayOperationCancelled();
			}
		} else {
			currentMenu.displayItemNotFound();
			currentMenu.pressEnterToReturn();
		}
	}
	
	/* EDIT ORDER METHODS */
	
	public void addLineItemToOrder() {
		// TODO write code
	}
	
	public void editLineItemOfOrder() {
		// TODO write code
	}
	
	public void removeLineItemFromOrder() {
		// TODO write code
	}
	
	public void editShippingStatusOfOrder() {
		//TODO write code
	}
	
	public void editCustomerOfOrder() {
		//TODO write code
	}
	
	public void editAddressOfOrder() {
		//TODO write code
	}
	
	/* INPUT & HELPER METHODS */
	
	public OrderLineItem createLineItem() {
		OrderLineItem lineItem = new OrderLineItem();
		lineItem.setParentOrder(order);
		lineItem.setProduct(product);
		lineItem.setProductQuantity(productQuantity);
	}
	
	public Customer inputCustomer() {
		System.out.print("Please enter the id of the customer to associate with the current order:");
		int customerId = input.nextInt();
		return customerModel.findCustomerById(customerId);
	}
	
	public boolean inputShippingStatus() {
		System.out.println("Has the order been shipped yet?");
		boolean shippingStatus = currentMenu.asksUserYesOrNo();
		return shippingStatus;
	}
	
	public Address inputShippedToAddressOfCustomer(Customer customer) {
		ArrayList<Address> knownCustomerAddresses = (ArrayList<Address>)addressModel.findAddressesByCustomer(customer);
		currentMenu.displayAddressList();
		System.out.println("Please select the row number (e.g. 1) of the address you want to link to this order shipment:");
		int thisRow = input.nextInt();
		return knownCustomerAddresses.get(thisRow);
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
	
	public boolean idIsInDatabase(int id) {
		if(orderModel.isOrderStoredWithId(id)) {
			return true;
		} else {
			return false;
		}
	}
}

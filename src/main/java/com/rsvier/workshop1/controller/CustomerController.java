package com.rsvier.workshop1.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.rsvier.workshop1.model.Customer;
import com.rsvier.workshop1.model.Validator;
import com.rsvier.workshop1.model.dao.CustomerDAO;
import com.rsvier.workshop1.model.dao.CustomerDAOImpl;
import com.rsvier.workshop1.view.AdminMainMenuView;
import com.rsvier.workshop1.view.CustomerView;
import com.rsvier.workshop1.view.UserMainMenuView;

public class CustomerController extends Controller {
	
	private CustomerView currentMenu;
	private CustomerDAO customerDao;
	private Scanner input = new Scanner(System.in);
	
	public CustomerController(CustomerView theView) {
		this.currentMenu = theView;
		customerDao = new CustomerDAOImpl();
	}
	
	@Override
	public void runView() {
		currentMenu.displayMenu();
		int userMenuChoice = Integer.parseInt(currentMenu.askUserForInput());
		switch (userMenuChoice) {
		case 1: findAllCustomers();
				break;
		case 2: findCustomer();
				break;
		case 3: addNewCustomer();
				break;
		case 4: updateCustomerPersonalia();
				break;
		case 5: updateCustomerAddresses();
				break;
		case 6: deleteCustomer();
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

	public void findAllCustomers() {
		ArrayList<Customer> allCustomers = (ArrayList<Customer>) customerDao.findAllCustomers();
		currentMenu.displayCustomerDetailsHeader();
		currentMenu.displayDivider();
		currentMenu.displayAllCustomers(allCustomers);
	}
	
	public void findCustomer() {
		Customer foundCustomer = new Customer();
		currentMenu.displayCanFindByIdAndName();
		String findThisCustomer = currentMenu.askUserForInput();
		if (Validator.isAnInt(findThisCustomer)) {
			foundCustomer = customerDao.findCustomerById(Integer.parseInt(findThisCustomer));
		} else {
			//TODO - account for the possbility of multiple customers with the same last name in DAO
			foundCustomer = customerDao.findCustomerByLastName(findThisCustomer);
		}
		currentMenu.displayCustomerDetailsHeader();
		currentMenu.displayDivider();
		currentMenu.displayCustomerDetails(foundCustomer);
	}
	
	public void addNewCustomer() {
		
	}
	
	public void updateCustomerPersonalia() {
		
	}
	
	public void updateCustomerAddresses() {
		
	}
	
	public void deleteCustomer() {
		
	}
}

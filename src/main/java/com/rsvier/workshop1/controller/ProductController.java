package com.rsvier.workshop1.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import com.rsvier.workshop1.model.Product;
import com.rsvier.workshop1.model.dao.ProductDAO;
import com.rsvier.workshop1.model.dao.ProductDAOImpl;
import com.rsvier.workshop1.view.AdminMainMenuView;
import com.rsvier.workshop1.view.ProductView;
import com.rsvier.workshop1.view.UserMainMenuView;
import com.rsvier.workshop1.model.Validator;

public class ProductController extends Controller {
	
	private ProductView currentMenu;
	private ProductDAO productModel;
	private Scanner input = new Scanner(System.in);
	
	public ProductController(ProductView theView) {
		this.currentMenu = theView;
	}

	@Override
	public void runView() {
		currentMenu.displayMenu();
		int userMenuChoice = Integer.parseInt(currentMenu.askUserForInput());
		switch (userMenuChoice) {
		case 1: findAllProducts();
				break;
		case 2: findProduct();
				break;
		case 3: addNewProduct();
				break;
		case 4: updateProduct();
				break;
		case 5: deleteProduct();
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
	
	public void findAllProducts() {
		productModel = new ProductDAOImpl();
		
		ArrayList<Product> allProducts = (ArrayList<Product>) productModel.findAllProducts();
		
		currentMenu.displayProductPropertiesHeader();
		currentMenu.displayLongDivider();
		currentMenu.displayAllProducts(allProducts);
		
		currentMenu.pressEnterToReturn();
		this.runView();
	}
	
	public void findProduct() {
		Product foundProduct = new Product();
		productModel = new ProductDAOImpl();
		
		currentMenu.displayCanFindByIdAndName(); // Indicates allowed search parameters to user
		String findThisProduct = currentMenu.askUserForInput();
		if (Validator.isAnInt(findThisProduct)) {
			// TODO Don't like using a Try-Catch here, improve later
			try { // if user input was an int
				foundProduct = productModel.findProductById(Integer.parseInt(findThisProduct));
			} catch (Exception ex) {
				System.out.println("Could not find a product with that ID.");
			}
		} else { 
			try { // if user input was not an int, a product name is assumed
				foundProduct = productModel.findProductByName(findThisProduct);
			} catch (Exception ex) {
				System.out.println("Could not find a product by that name.");
			}
		}
		currentMenu.displayProductPropertiesHeader();
		currentMenu.displayLongDivider();
		currentMenu.displayProductProperties(foundProduct);
		
		currentMenu.pressEnterToReturn();
		this.runView();
	}
	
	public void addNewProduct() {
		Product productToAdd = new Product();
		productModel = new ProductDAOImpl();
		
		System.out.println("Please enter product details below:");

		String name = inputName();
		productToAdd.setProductName(name);
		
		String price = inputPrice();
		productToAdd.setPrice(new BigDecimal(price));
		
		int stockQuantity = inputStockQuantity();
		productToAdd.setStockQuantity(stockQuantity);
		
		int producedYear = inputYear();
		productToAdd.setProducedYear(producedYear);
		
		String country = inputCountry();
		productToAdd.setCountry(country);
		
		String grapeVariety = inputGrapeVariety();
		productToAdd.setGrapeVariety(grapeVariety);
		
		double alcoholPercentage = inputAlcoholPercentage();
		productToAdd.setAlcoholPercentage(alcoholPercentage);
		
		productModel.createProduct(productToAdd);
		currentMenu.displayCreateSuccess();
		
		currentMenu.pressEnterToReturn();
		this.runView();
	}
	
	public void deleteProduct() {
		Product productToDelete = new Product();
		productModel = new ProductDAOImpl();
		
		int id = inputValidProductId();
		System.out.println(id);
		productToDelete.setProductId(id);
		if(idIsInDatabase(id)) {
			System.out.println(productToDelete.getProductId());
			currentMenu.displayDeletionConfirmationPrompt(); // Require confirmation
			boolean yesOrNo = currentMenu.asksUserYesOrNo();
			if (yesOrNo) { // user answered yes
				boolean deleteSuccess = productModel.deleteProduct(productToDelete);
				if (deleteSuccess) {
					currentMenu.displayDeleteSuccess();
					currentMenu.pressEnterToReturn();
					this.runView();
				} else {
					currentMenu.displayOperationFailed();
					currentMenu.pressEnterToReturn();
					this.runView();
				}
			} else {
				currentMenu.displayOperationCancelled();
				currentMenu.pressEnterToReturn();
				this.runView();
			}
		} else {
			currentMenu.displayItemNotFound();
			currentMenu.pressEnterToReturn();
			this.runView();
		}
	}
	
	public void updateProduct() {
		currentMenu.displayProductUpdateMenu();
		
		String userChoice = input.nextLine(); 
		if(!Validator.validateMenuChoice(userChoice)) { // Necessary to return the menu if input was deemed invalid
			System.out.println("You did not enter a valid number. \n");
			currentMenu.displayProductUpdateMenu();
		}
		
		int userChoiceNumber = Integer.parseInt(userChoice);
		switch(userChoiceNumber) {
			case 1: editProductName();
					break;
			case 2: editProductPrice();
					break;
			case 3: editProductStockQuantity();
					break;
			case 4: editProductYear();
					break;
			case 5: editProductCountryOfOrigin();
					break;
			case 6: editProductGrapeVariety();
					break;
			case 7: editProductAlcoholContent();
					break;
			case 9:	this.runView();
					break;
			default: System.out.println("Invalid choice. \n");
					currentMenu.displayProductUpdateMenu();
		}
	}
	
	/* EDIT PRODUCT METHODS */
	
	public void editProductName() {
		Product productToUpdate = new Product();
		productModel = new ProductDAOImpl();
		// requires an id from user to identify which product to update
		int id = inputValidProductId();
		// checks whether product is in database with provided id
		if(idIsInDatabase(id)) { // proceeds with update if found
			// Populates product with current data
			productToUpdate = productModel.findProductById(id);
			
			String name = inputName();
			productToUpdate.setProductName(name);
			runUpdateOnProduct(productToUpdate);
		} else { // alerts user if id is not found in database
			alertUserAndReturn();
		} 	
	}
	
	public void editProductPrice() {
		Product productToUpdate = new Product();
		productModel = new ProductDAOImpl();

		int id = inputValidProductId();
		
		if(idIsInDatabase(id)) {
			productToUpdate = productModel.findProductById(id);
			
			String price = inputPrice();
			productToUpdate.setPrice(new BigDecimal(price));
			runUpdateOnProduct(productToUpdate);
		} else {
			alertUserAndReturn();
		}
	}
	
	public void editProductStockQuantity() {
		Product productToUpdate = new Product();
		productModel = new ProductDAOImpl();

		int id = inputValidProductId();
		
		if(idIsInDatabase(id)) {
			productToUpdate = productModel.findProductById(id);
			
			int stockQuantity = inputStockQuantity();
			productToUpdate.setStockQuantity(stockQuantity);
			runUpdateOnProduct(productToUpdate);
		} else { 
			alertUserAndReturn();
		}
	}
	
	public void editProductYear() {
		Product productToUpdate = new Product();
		productModel = new ProductDAOImpl();

		int id = inputValidProductId();
		
		if(idIsInDatabase(id)) {
			productToUpdate = productModel.findProductById(id);
			
			int productYear = inputYear();
			productToUpdate.setProducedYear(productYear);
			runUpdateOnProduct(productToUpdate);
		} else { 
			alertUserAndReturn();
		}
	}
	
	public void editProductCountryOfOrigin() {
		Product productToUpdate = new Product();
		productModel = new ProductDAOImpl();

		int id = inputValidProductId();

		if(idIsInDatabase(id)) {
			productToUpdate = productModel.findProductById(id);
			
			String country = inputCountry();
			productToUpdate.setCountry(country);
			runUpdateOnProduct(productToUpdate);
		} else {
			alertUserAndReturn();
		}
	}
	
	public void editProductGrapeVariety() {
		Product productToUpdate = new Product();
		productModel = new ProductDAOImpl();

		int id = inputValidProductId();

		if(idIsInDatabase(id)) {
			productToUpdate = productModel.findProductById(id);
			
			String grapeVariety = inputGrapeVariety();
			productToUpdate.setGrapeVariety(grapeVariety);
			runUpdateOnProduct(productToUpdate);
		} else {
			alertUserAndReturn();
		}
	}
	
	public void editProductAlcoholContent() {
		Product productToUpdate = new Product();
		productModel = new ProductDAOImpl();

		int id = inputValidProductId();
		
		if(idIsInDatabase(id)) {
			productToUpdate = productModel.findProductById(id);
			
			System.out.print("Enter an alcohol percentage (e.g. 14.2):");
			String userInputAlcoholPercentage = input.nextLine();
			productToUpdate.setAlcoholPercentage(Double.parseDouble(userInputAlcoholPercentage));
			
			runUpdateOnProduct(productToUpdate);
		} else {
			alertUserAndReturn();
		}
	}
	
	/* INPUT & HELPER METHODS */
	
	public String inputName() {
		System.out.print("Enter a name:");
		String name = input.nextLine();
		if(name.isEmpty()) { // can't be empty
			return inputName();
		} else {
			return name;
		}
	}
	
	public String inputPrice() {
		System.out.print("Enter a price (e.g. 13.37):");
		String priceAsString = input.nextLine();
		if(!Validator.validatePrice(priceAsString)) {
			return inputPrice();
		} else {
			return priceAsString;
		}
	}
	
	public int inputStockQuantity() {
		System.out.print("Enter a new stock amount:");
		String stockAsString = input.nextLine();
		if(!Validator.isAPositiveOrZeroInt(stockAsString)) {
			return inputStockQuantity();
		} else {
			int stockQuantity = Integer.parseInt(stockAsString);
			return stockQuantity;
		}
	}
	
	public int inputYear() {
		System.out.print("Enter a production year:");
		String yearAsString = input.nextLine();
		if(!Validator.isAnInt(yearAsString)) {
			return inputYear();
		} else {
			int year = Integer.parseInt(yearAsString);
			return year;
		}
	}
	
	public String inputCountry() {
		System.out.print("Enter a country:");
		String country = input.nextLine();
		if(!Validator.validateCountry(country)) { 
			return inputCountry();
		} else {
			// Normalize input to eliminate duplication in database due to case sensitivity
			return Character.toUpperCase(country.charAt(0)) + country.substring(1);
		}
	}
	
	public String inputGrapeVariety() {
		System.out.print("Enter a grape variety:");
		String grapeVariety = input.nextLine();
		if(grapeVariety.isEmpty()) { // can't be empty
			return inputCountry();
		} else {
			return grapeVariety;
		}
	}
	
	public double inputAlcoholPercentage() {
		System.out.println("Enter an alcohol percentage:");
		String percentageAsString = input.nextLine();
		if(!Validator.validateAlcoholPercentage(percentageAsString)) {
			return inputAlcoholPercentage();
		} else {
			double alcoholPercentage = Double.parseDouble(percentageAsString);
			return alcoholPercentage;
		}
	}
	
	public int inputValidProductId() {
		currentMenu.promptUserForItemId();
		String attemptAtId = input.nextLine();
		while (!Validator.isAnInt(attemptAtId)) {
			System.out.println("You did not enter a valid product id. Please try again:");
			attemptAtId = input.nextLine();
		}
		int id = Integer.parseInt(attemptAtId);
		return id;
	}
	
	public void alertUserAndReturn() {
		currentMenu.displayItemNotFound();
		currentMenu.pressEnterToReturn();
		this.runView();
	}
	
	public boolean idIsInDatabase(int id) {
		return productModel.isProductStoredWithId(id);
	}
	
	public void runUpdateOnProduct(Product productToUpdate) {
		boolean isSuccessful = productModel.updateProduct(productToUpdate);
		if (isSuccessful) {
			currentMenu.displayUpdateSuccess();
			currentMenu.pressEnterToReturn();
			this.runView();
		} else {
			currentMenu.displayOperationFailed();
			currentMenu.pressEnterToReturn();
			this.runView();
		}
	}
}

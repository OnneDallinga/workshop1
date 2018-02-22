package com.rsvier.workshop1.view;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Product;

public class ProductView extends View {
	
	@Override
	public void displayMessage() {
	}
	
	@Override
	public void displayMenu() { // Constructs the menu
		displayMenuHeader();
		displayDivider();
		displayMenuOptions();
	}
	
	public void displayMenuHeader() {
		System.out.println("Product Menu \n");
	}
	
	public void displayMenuOptions() {
		System.out.print("1. View product inventory \n" +
						 "2. Find a product" +
						 "3. Add new product \n" +
						 "4. Update product \n" +
						 "5. Delete product \n" +
						 "9. Return to main menu \n" +
						 "0. Exit program \n" +
						 "\n");
	}
	
	public void displayProductPropertiesHeader() {
		System.out.printf("%-3d %-10s %-5d.2 %-3d %-5d %-10s %-10s %-3d.2 \n",
						   "id",
						   "name",
						   "price",
						   "# in stock",
						   "year",
						   "country",
						   "variety",
						   "alc. %");
	}
	
	public void displayProductProperties(Product product) {
		System.out.printf("%-3d %-10s %-5d.2 %-3d %-5d %-10s %-10s %-3d.2 \n",
						   product.getProductId(),
						   product.getProductName(),
						   product.getPrice(),
						   product.getStockQuantity(),
						   product.getProducedYear(),
						   product.getCountry(),
						   product.getGrapeVariety(),
						   product.getAlcoholPercentage());
	}
	
	public void displayAllProducts(ArrayList<Product> allProducts) {
		for (Product product : allProducts) {
			displayProductProperties(product);
		}
	}
	
	public void displayProductUpdateMenu() {
		System.out.print("What would you like to update? \n" +
						 "\n" +
						 "1. Name \n" +
						 "2. Price \n" +
						 "3. Stock \n" +
						 "4. Year of production \n" +
						 "5. Country of origin \n" +
						 "6. Grape variety \n" +
						 "7. Alcohol content \n" +
						 "9. Return to the product menu \n" +
						 "0. Exit program \n" +
						 "\n");
	}
}

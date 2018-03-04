package com.rsvier.workshop1.view;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Order;
import com.rsvier.workshop1.model.Product;
import com.rsvier.workshop1.model.OrderLineItem;

public class OrderView extends View {
	
	@Override
	public void displayMessage() {
	}
	
	@Override
	public void displayMenu() {
		displayMenuHeader();
		displayDivider();
		displayMenuOptions();
	}
	
	public void displayMenuHeader() {
		System.out.println("Order Menu \n");
	}
	
	public void displayMenuOptions() {
		System.out.print("1. View all orders \n" +
						 "2. View pending orders only \n" +
						 "3. View completed orders only \n" +
						 "4. Find an order" +
						 "5. Add new order \n" +
						 "6. Update order \n" +
						 "7. Delete order \n" +
						 "9. Return to main menu \n" +
						 "0. Exit program \n" +
						 "\n");
	}
	
	public void displayOrderPropertiesHeader() {
		System.out.printf("%-5d %-5d %-5d %-7d.2 %-7s \n",
						   "id",
						   "customer id",
						   "# items",
						   "total price",
						   "shipping status");
	}
	
	public void displayOrderProperties(Order order) {
		System.out.printf("%-5d %-5d %-5d %-7d.2 %-7s \n",
						   order.getOrderId(),
						   order.getCustomerId(),
						   order.getOrderItemsTotal(),
						   order.getOrderPriceTotal(),
						   order.isShipped());
	}
	
	public void displayAllOrders(ArrayList<Order> allOrders) {
		for (Order order : allOrders) {
			displayOrderProperties(order);
		}
	}
	
	// TODO Figure out an elegant way to display this
	public void displayOrderItemListHeader() {
	}
	
	// TODO Figure out an elegant way to display this > autoincrement row # and incorporate product name
	public void displayOrderItemListDetails(ArrayList<OrderLineItem> orderItemList, ArrayList<Product> productsInOrderList) {
		
	}
	
	public void displayOrderWithItemDetails(Order order, ArrayList<OrderLineItem> orderItemList, ArrayList<Product> productsInOrderList) {
		displayOrderPropertiesHeader();
		displayOrderProperties(order);
		displayOrderItemListHeader();
		displayOrderItemListDetails(orderItemList, productsInOrderList);
	}
	
	public void displayOrderUpdateMenu() {
		System.out.print("What would you like to update? \n" +
						 "\n" +
						 "1. Associated customer \n" +
						 "2. Items in order \n" +
						 "3. Shipping status \n" +
						 "4. Associated shipping address \n" +
						 "9. Return to the order menu \n" +
						 "\n");
	}
	
	public void askWhichOrder() {
		System.out.print("Please provide ID-number of order.");
	}

}

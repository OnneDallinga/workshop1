package com.rsvier.workshop1.view;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Order;
import com.rsvier.workshop1.model.Product;
import com.rsvier.workshop1.model.OrderLineItem;

public class OrderMenu extends View {
	
	@Override
	public void displayMessage() {
	}
	
	public void displayMenuHeader() {
		System.out.println("Order Menu \n");
	}
	
	public void displayMenuOptions() {
		System.out.print("1. View all orders \n" +
						 "2. Find an order" +
						 "3. Add new order \n" +
						 "4. Update order \n" +
						 "5. Delete order \n" +
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
						 "1. Order id \n" +
						 "2. Associated customer id \n" +
						 "3. Number of items in order \n" +
						 "4. Total price of order \n" +
						 "5. Shipping status \n" +
						 "9. Return to the order menu \n" +
						 "\n");
	}
	
	public void askWhichOrder() {
		System.out.print("Please provide ID-number of order.");
	}

}

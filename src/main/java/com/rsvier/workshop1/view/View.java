package com.rsvier.workshop1.view;
import java.io.*;
import java.util.*;

import com.rsvier.workshop1.controller.Controller;
import com.rsvier.workshop1.useraccounts.*;

public abstract class View<K> { //parent version
	protected int possibleUserChoice;
	protected int menuChoice;
	protected ArrayList<Integer> possibleMenuOptions;
	protected HashMap<Integer, View> menuOptions;
	protected boolean validUserChoice;
	protected User user;
	protected View userMenuChoice;
	
	public void displayMessage() {}; //This runs when the menu is loaded. Welcome text etc.
	public User getUser() { //Use this to get info on the current logged in user.
		return user;
	};
	public View() {} //add HashMap info here and load the possibleMenuOptions
	public View getUserMenuChoice() {
		return userMenuChoice;
	}
	
	public int asksUserForMenuChoice(HashMap<Integer, K> allowedUserChoices) {
		int userInput = -1;
		boolean validInput = false; // Don't allow the user to exit unless he enters something valid
		while (!validInput) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please select an option: ");
			try{
				userInput = Integer.parseInt(br.readLine());
				if (userInput == 0) {
					System.out.println("Program is closing..");
					System.exit(0); // 0 is always exit
				}
				if (allowedUserChoices.get(userInput) != null) { // If it's null then it's not a valid choice
				validInput = true;
				}
				else {
					System.out.println("You entered an invalid input");
				}
			}
			catch(Exception inputIsNotAnIntError){
					System.out.println("You entered an invalid input"); // This point should not be reachable
			}
		}
		return userInput;
	}
	
	public String asksUserForUserChoice(ArrayList<String> listOfUsers) {
		String userInput = "";
		boolean validInput = false; // Don't allow the user to exit unless he enters something valid
		while (!validInput) {
			System.out.println(listOfUsers);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please select a user or enter 9 to return");
			try{
				userInput = br.readLine();
				System.out.println(userInput);
				if (userInput.equals("9")) {
					validInput = true;
					break;
				}
				for (String user : listOfUsers) {
					System.err.println(user);
					if (user.equals(userInput)) {
						System.err.println("HOERA");
						validInput = true;
						break;
					}
				}
				System.out.println("You entered an invalid input");
			}
			catch(Exception inputIsNotValidError){
					System.out.println("You entered an invalid input"); // This point should not be reachable
			}
		}
		return userInput;
	}
	
}

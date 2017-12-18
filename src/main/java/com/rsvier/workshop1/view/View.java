package com.rsvier.workshop1.view;
import java.io.*;
import java.util.*;
import com.rsvier.workshop1.useraccounts.*;

public abstract class View { //parent version
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
	
	
	public int asksUserForMenuChoice() { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please select an option: ");
		int userInput = -1;
		try{
			userInput = Integer.parseInt(br.readLine());
			if (userInput == 0) System.exit(0); // 0 is always exit
			}catch(Exception inputIsNotAnIntError){
				System.err.println("You entered an invalid input");
				asksUserForMenuChoice();
			}
		return userInput;
	}
}

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
		userMenuChoice = menuOptions.get(possibleUserChoice);
		return userMenuChoice;
	}
	
	
	public int asksUserForMenuChoice(ArrayList<Integer> possibleMenuOptions) { 
		//Asks user for a menu choice. Must be a valid Integer defined in the possibleMenuOptions.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please select an option: ");
		while (validUserChoice == false) {
			try{
				possibleUserChoice = Integer.parseInt(br.readLine());
				if (possibleUserChoice == 0) System.exit(0); // 0 is always exit
				for (int possibleOption : possibleMenuOptions) {
					if (possibleUserChoice == possibleOption) {
						return possibleUserChoice;
					}
				}
			}catch(Exception inputIsNotAnIntError){
				System.err.println("You entered an invalid input");
			}
		}
		return 0; // this point cannot be reached.
	}
}

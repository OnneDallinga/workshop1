package com.rsvier.workshop1.view;
import java.io.*;
import java.util.*;
import com.rsvier.workshop1.useraccounts.*;

public abstract class View { //parent version
	protected int possibleUserChoice;
	protected int menuChoice;
	protected ArrayList<Integer> possibleMenuOptions;
	protected boolean validUserChoice;
	protected User user;
	
	public void displayMessage() {};
	public User getUser() {
		return user;
	};
	
	public int asksUserForMenuChoice() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please select an option: ");
		while (validUserChoice == false) {
			try{
				possibleUserChoice = Integer.parseInt(br.readLine());
				for (int possibleOption : possibleMenuOptions) {
					if (possibleUserChoice == possibleOption) {
						return possibleUserChoice;
					}
				}
			}catch(NumberFormatException inputIsNotAnIntError){
				System.err.println("You entered an invalid input");
			}
		}
		return 0; // this point cannot be reached.
	}
}

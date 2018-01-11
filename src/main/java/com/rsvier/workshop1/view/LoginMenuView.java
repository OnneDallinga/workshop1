package com.rsvier.workshop1.view;
import java.io.*;
import com.rsvier.workshop1.controller.*;
import com.rsvier.workshop1.model.RetrieveUserInfoModel;
import com.rsvier.workshop1.useraccounts.*;

public class LoginMenuView extends View {
	
	@Override		
	public void displayMessage() {
		System.out.println("Welcome to the login screen. Please enter your username and password to login. Press 0 to exit. "
					+ "Press 1 to create a new user account.");
		}
	public String[] asksUserForLogin() {
		String[] userInput = new String[2];
		String username = null;
		String password = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try { // ask for their username. If user enters a 0: exit. If user enters a 1: Go to account creation.
			System.out.println("Username: ");
			username = br.readLine();
			if (username.equals("0")) System.exit(0); // 0 = exit. 1 = account creation.
			if (username.equals("1")) {
				return new String[0];
			}
		}
		catch (IOException invalidInput){
			System.out.println("Invalid input");
		}
		try {
			System.out.println("Password: ");
			password = br.readLine();
			if (password.equals("0")) System.exit(0); // 0 = exit. 1 = account creation.
			if (username.equals("1")) {
				return new String[0];
			}
		}
		catch (IOException invalidInput){
			System.out.println("Invalid input");
		}
		userInput[0] = username;
		userInput[1] = password;
		return userInput;
	}
}

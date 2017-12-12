package com.rsvier.workshop1.view;
import java.io.*;
import com.rsvier.workshop1.controller.*;

public class LoginMenuView extends View {
	private String username;
	private String password;
	private boolean validatedUser;
	
	@Override		
	public void displayMessage() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) { // always true unless the user hits the break (successful login) or enters 0 (system exit)
			System.out.println("Welcome to the login screen. Please enter your username and password to login. Press 0 to exit. "
					+ "Press 1 to create a new user account.");
			try { // ask for their username. If user enters a 0: exit. If user enters a 1: Go to account creation.
				System.out.println("Username: ");
				username = br.readLine();
				if (username.equals("0")) System.exit(0);
				if (username.equals("1")) {
					new Controller(new UserCreationView()).runView();
					continue;
				}
			}
			catch (IOException invalidInput){
				System.out.println("Invalid input");
				continue;
			}
			try {
				System.out.println("Password: ");
				password = br.readLine();
				if (password.equals("0")) System.exit(0);
				validatedUser = new ValidationCheck().validateUser(username, password);
				if (validatedUser == false) {
					System.out.println("Incorrect username or password");
					continue;
				}
				System.out.println("You logged in successfully.");
				break;
			}
			catch (IOException invalidInput){
				System.out.println("Invalid input");
				continue;
			}
		}
	}
	public String getUsername () {
		return username;
	}

}

package com.rsvier.workshop1.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.rsvier.workshop1.model.ValidationModel;

public class UserCreationView extends View {
	
	@Override
	public void displayMessage() {
		System.out.println("Welcome. Please enter your information below in order to create an account."
				+ " Press 0 at any point to exit.");
	}
	
	public String askUserForInput(String customerProperty) {
		String userInput = "";
		switch (customerProperty) {
		case "username":
			System.out.println("Please enter your username:");
			break;
		case "password":
			System.out.println("Please enter a password");
			break;
		case "first_name":
			System.out.println("Please enter your first name.");
			break;
		case "last_name":
			System.out.println("Please enter your last name.");
			break;
		case "last_name_preposition":
			System.out.println("Please enter your last name preposition. Leave blank if not relevant.");
			break;
		case "email":
			System.out.println("Please enter your e-mail address.");
			break;
		case "phone_number":
			System.out.println("Please enter your (10 digit) phone number.");
			break;
		default:
			System.out.println("YOUR DATABASE HAS CHANGED");
			System.exit(0);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			userInput = br.readLine();
			if (userInput.equals("0")) {
				System.out.println("Program is closing..");
				System.exit(0);
			}
		}
		catch (IOException notAValidChoice) {
			notAValidChoice.printStackTrace();
		}
		return userInput;
	}
}

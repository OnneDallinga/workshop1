package com.rsvier.workshop1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.rsvier.workshop1.controller.Main;
import com.rsvier.workshop1.database.DataSource;

public class SendInfoToDatabaseModel extends Model {

	public boolean createNewUser(ArrayList<String> necessaryCustomerInformation, ArrayList<String> newUser) { // creates a new user
		String query = "INSERT INTO customer (first_name, last_name, last_name_preposition, email, phone_number) VALUES (?,?,?,?,?)";
		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1, newUser.get(2));
			statement.setString(2, newUser.get(3));
			statement.setString(3, newUser.get(4));
			statement.setString(4, newUser.get(5));
			statement.setString(5, newUser.get(6));
			statement.executeUpdate();
			if (!Main.hikariEnabled) connection.close(); // necessary for the JDBC
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		query = "SELECT id FROM customer WHERE first_name = (?)";
		ResultSet resultSet = null;
		String id = null;
		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1,  newUser.get(2));
			resultSet = statement.executeQuery();
			id = resultSet.getString(0);
			if (!Main.hikariEnabled) connection.close(); // necessary for the JDBC
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		query = "INSERT INTO account (username, password, owner_type) VALUES (?,?,?) WHERE id = " + id;
		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);){
			statement.setString(1,  newUser.get(0));
			statement.setString(2,  newUser.get(1));
			statement.setString(3,  "whatever");
			statement.executeUpdate();
			if (!Main.hikariEnabled) connection.close(); // necessary for the JDBC
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return true;
	}
	
	public boolean deleteUserFromDatabase(String username) {
		String query = ("delete from account where username = " + username);
		return databaseQuery(query);
	}
	
	private boolean databaseQuery(String query) {
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Load the driver
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rsvier", "Onne", "Once!UponAT1me");
			// Connect to the database
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			connection.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}

package com.rsvier.workshop1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RetrieveUserInfoModel extends Model {
	public int retrieveUserId(String username) {
		username = "'" + username + "'";
		String query = "select userID from userLoginInformation where username = "
				+ username + ";";
		ArrayList<String> queryResult = databaseQuery(query);
		return Integer.parseInt(queryResult.get(0));
	}

	public boolean retrieveAdminStatus(int userID) {
		String query = "select isAdmin from userInformation where userID = "
				+ userID + ";";
		ArrayList<String> queryResult = databaseQuery(query);
		if (Integer.parseInt(queryResult.get(0)) == 1) return true;
		return false;
	}
	
	public boolean login(String username, String password) {
		username = "'" + username + "'";
		String query = "Select password from userLoginInformation where username = "
				+ username + ";";
		ArrayList<String> queryResult = databaseQuery(query);
		if (queryResult.isEmpty()) return false;
		if (queryResult.get(0).equals(password)) return true;
		return false;
	}
	
	public void showAllUsers() {
		String query = "Select userID, voornaam, achternaam from userInformation where userID > 0";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Load the driver
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Onne", "Once!UponAT1me");
			// Connect to the database
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int i = 1;
				System.out.println(resultSet.getObject(i));
				i++;
			}
			connection.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<String> databaseQuery(String query) {
		ResultSet resultSet = null;
		ArrayList<String> resultSetAsArrayList = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Load the driver
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Onne", "Once!UponAT1me");
			// Connect to the database
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int i = 1;
				resultSetAsArrayList.add(resultSet.getString(i));
				i++;
			}
			connection.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSetAsArrayList;
	}
}

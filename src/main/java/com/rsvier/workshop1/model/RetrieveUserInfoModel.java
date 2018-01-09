package com.rsvier.workshop1.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class RetrieveUserInfoModel extends Model {
	
/*	public void test() {
		try {
			URL url = new URL("C:\\Users\\Onne\\git\\Workshop1\\pom.xml");
			Document document = new XMLReader().parse(url);
			document.
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} */
	
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
	
	public ArrayList<String> showAllUsernames() {
		String query = "Select username from userLoginInformation where userID > 0";
		ArrayList<String> allOfTheUsers = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Load the driver
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Onne", "Once!UponAT1me");
			// Connect to the database
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			// TODO: Add different class to handle this printing.
			while (resultSet.next()) {
				allOfTheUsers.add(resultSet.getObject(1) + "");
			}
			connection.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return allOfTheUsers;
	}
	
	public ArrayList<String> showAllUsers() {
		String query = "Select * from userInformation where userID > 0";
		ArrayList<String> allOfTheUsers = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Load the driver
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Onne", "Once!UponAT1me");
			// Connect to the database
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			// TODO: Add different class to handle this printing.
			while (resultSet.next()) {
				allOfTheUsers.add(resultSet.getObject(1) + " ");
				allOfTheUsers.add(resultSet.getObject(2) + " ");
				allOfTheUsers.add(resultSet.getObject(3) + " ");
				allOfTheUsers.add(resultSet.getObject(4) + " ");
				allOfTheUsers.add(resultSet.getObject(5) + " ");
			}
			connection.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return allOfTheUsers;
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

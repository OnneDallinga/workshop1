package com.rsvier.workshop1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RetrieveUserInfoModel {
	public int retrieveUserId(String username) {
		username = "'" + username + "'";
		String query = "select userID from userLoginInformation where username = "
				+ username + ";";
		ArrayList<String> queryResult = databaseQuery(username, query);
		return Integer.parseInt(queryResult.get(0));
	}

	public boolean retrieveAdminStatus(int userID) {
		String query = "select isAdmin from userInformation where userID = "
				+ userID + ";";
		String userIDAsString = Integer.toString(userID);
		ArrayList<String> queryResult = databaseQuery(userIDAsString, query);
		if (Integer.parseInt(queryResult.get(0)) == 1) return true;
		return false;
	}
	
	private ArrayList<String> databaseQuery(String thingtoQuery, String query) {
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

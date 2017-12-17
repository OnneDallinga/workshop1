package com.rsvier.workshop1.model;

import java.sql.*;

public class ValidationCheckModel {
	private int userID;

	public boolean validateUser(String username, String password) {
		try {
			username = "'" + username + "'"; 
			Class.forName("com.mysql.jdbc.Driver");
			// Load the driver
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Onne", "Once!UponAT1me");
			// Connect to the database
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select password, userID from userLoginInformation where username "
			+ "= " + username + ";");
			if (resultSet.next()) {
				String passwordInDatabase = resultSet.getString(1);
				if (passwordInDatabase.equals(password)) {
					userID = resultSet.getInt(2);
					connection.close();
					return true;
				}
			}
			connection.close();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public int getUserID() {
		return userID;
	}
}
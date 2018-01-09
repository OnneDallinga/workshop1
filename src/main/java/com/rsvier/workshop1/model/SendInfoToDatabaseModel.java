package com.rsvier.workshop1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SendInfoToDatabaseModel extends Model {
	public boolean deleteUserFromDatabase(String username) {
		String query = ("delete from userLoginInformation where username = " + username);
		return databaseQuery(query);
	}
	
	
	
	private boolean databaseQuery(String query) {
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Load the driver
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Onne", "Once!UponAT1me");
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

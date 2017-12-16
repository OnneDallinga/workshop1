package com.rsvier.workshop1.model;

import java.sql.*;

public class ValidationCheckModel {

	public boolean validateUser(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Load the driver
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "Onne", "Once!UponAT1me");
			// Connect to the database
			CallableStatement callableStatement = connection.prepareCall("{? = call correctLogin(?, ?)}");
			callableStatement.setString(2,  username);
			callableStatement.setString(3,  password);
			callableStatement.registerOutParameter(1,  Types.INTEGER);
			callableStatement.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
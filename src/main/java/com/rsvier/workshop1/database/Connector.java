package com.rsvier.workshop1.database;

import java.sql.*;

public class Connector {

	private Connection connection = null;
	
	public Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException ex) {
				System.out.println("Could not load driver");
				System.exit(1);
			}
			try {
				connection = DriverManager.getConnection(
								"jdbc:mysql://localhost/mydb",
								"Battlehenkie",
								"koekjes");
			} catch (SQLException ex){
				System.out.println("Invalid url, user and/or password");
				System.exit(1);
			}
		}
		return connection;
	}
}
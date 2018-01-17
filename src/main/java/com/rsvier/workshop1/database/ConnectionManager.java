package com.rsvier.workshop1.database;

import java.sql.*;

public class ConnectionManager {

	private Connection connection;
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost/mydb";
	private String dbUser = "GeertL";
	private String dbPass = "k44sBl0kJe$";
	
	public Connection getConnection() {
			try {
				Class.forName(dbDriver);
			} catch (ClassNotFoundException ex) {
				System.out.println("Error: could not load driver");
				System.exit(1);
			}
			try {
				this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			} catch (SQLException ex){
				System.out.println("Invalid: check url, user, password");
				System.exit(1);
			}
		return connection;
	}
}
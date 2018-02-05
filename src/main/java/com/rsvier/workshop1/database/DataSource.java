package com.rsvier.workshop1.database;

import java.sql.*;
import com.rsvier.workshop1.controller.Main;
import com.zaxxer.hikari.*;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
 
    static {
    	if (Main.hikariEnabled) { //depends on whether user has enabled Hikari or not
    		config = new HikariConfig("hikari.properties");
    		ds = new HikariDataSource( config );
    	}
    }
 
    public static Connection getConnection() throws SQLException {
    	if (Main.hikariEnabled)	return ds.getConnection(); //depends on whether user has enabled Hikari or not
    	try {
    	  	Class.forName("com.mysql.jdbc.Driver");
    	  	// Load the driver
    	  	//TODO: Figure out how to pass XML dbvars to static method
    	  	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rsvier", "GeertL", "k44sBl0kJe$");
    	  	// Connect to the database
    	  	return connection;
    	}
    	catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
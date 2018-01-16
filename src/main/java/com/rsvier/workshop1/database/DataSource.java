package com.rsvier.workshop1.database;

import java.sql.*;
import com.rsvier.workshop1.controller.Main;
import com.zaxxer.hikari.*;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
 
    static {
    	if (Main.hikariEnabled) { //depends on whether user has enabled Hikari or not
    		config.setJdbcUrl( "jdbc:mysql://localhost/rsvier" );
    		config.setUsername( "Onne" );
    		config.setPassword( "Once!UponAT1me" );
    		config.addDataSourceProperty( "cachePrepStmts" , "true" );
    		config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
    		config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
    		ds = new HikariDataSource( config );
    	}
    }
 
    private DataSource() {}
 
    public static Connection getConnection() throws SQLException {
    	if (Main.hikariEnabled)	return ds.getConnection(); //depends on whether user has enabled Hikari or not
    	try {
    	  	Class.forName("com.mysql.jdbc.Driver");
    	  	// Load the driver
    	  	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rsvier", "Onne", "Once!UponAT1me");
    	  	// Connect to the database
    	  	return connection;
    	}
    	catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
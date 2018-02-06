package com.rsvier.workshop1.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.DriverManager;
import com.rsvier.workshop1.database.DataSource;
import com.rsvier.workshop1.model.Order;
import com.ibatis.common.jdbc.ScriptRunner;

public class DatabaseBuilderDAOImpl implements DatabaseBuilderDAO {
	
	private Logger logger = Logger.getLogger(CustomerDAOImpl.class.getName());

	@Override
	public boolean isDatabaseInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean initializeDatabase() {
			String aSQLScriptFilePath = "create.sql";
			Connection conn = null;
			Reader reader = null;
			boolean success = true;
			try {
				conn = DataSource.getConnection();
				logger.info("Connected to database.");
				// Initialize object for ScripRunner
				ScriptRunner sr = new ScriptRunner(conn, false, false);

				// Give the input file to Reader
				reader = new BufferedReader(
	                               new FileReader(aSQLScriptFilePath));

				// Execute script
				sr.runScript(reader);

			} catch (Exception e) {
				System.err.println("Failed to Execute" + aSQLScriptFilePath
						+ " The error is " + e.getMessage());
				success = false;
			}
			finally {
				// close file reader
				if (reader != null) {
					try {
					reader.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				// close db connection
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return success;
	}
}

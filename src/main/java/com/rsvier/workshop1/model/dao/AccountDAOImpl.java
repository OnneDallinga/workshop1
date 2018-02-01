package com.rsvier.workshop1.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import com.rsvier.workshop1.controller.Main;
import com.rsvier.workshop1.database.DataSource;

public class AccountDAOImpl implements AccountDAO {
	
	private Logger logger = Logger.getLogger(AccountDAOImpl.class.getName());

	@Override
	public boolean login(String username, String password) {

			String query = "Select password from account where username = (?)";
			Boolean result = false;
			try (Connection connection = DataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(query);){
					logger.info("Connected to database");
					statement.setString(1, username);
					ResultSet resultSet = statement.executeQuery();
					while (resultSet.next()) {
						if (password.equals(resultSet.getString(1))) {
							logger.info("Login successful");
							return true;	
						}
					}
					logger.info("Login failed");
					if (!Main.hikariEnabled) connection.close(); // necessary for the JDBC
				}
			catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return result;
	}


	@Override
	public boolean createAccount() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeUsername() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int getUserID(String username) {
		int userID = -1;	
		String query = "Select customer_id from account where username = (?)";
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);){
				logger.info("Connected to database");
				statement.setString(1, username);
				ResultSet resultSet = statement.executeQuery();
				if (!resultSet.next()) {
					return -99;
				}
				while (resultSet.next()) {
					userID = resultSet.getInt(1);
					logger.info("User ID retrieved");
				}
				if (!Main.hikariEnabled) connection.close(); // necessary for the JDBC
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userID;
}
	
	public ArrayList<String> retrieveAccountProperties() { // retrieves the information we need from the customer from the tables
		ArrayList<String> resultSetAsArrayList = new ArrayList<>();
		String query = "select column_name from information_schema.columns\r\n" + 
				" where table_name = 'account'";
		String query2 =  "select column_name from information_schema.columns\r\n" + 
				" where table_name = 'customer'";
		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				int i = 1;
				resultSetAsArrayList.add(resultSet.getString(i));
				i++;
			}
			PreparedStatement statement2 = connection.prepareStatement(query2);
			ResultSet resultSet2 = statement2.executeQuery();
			while (resultSet2.next()) {
				int i = 1;
				resultSetAsArrayList.add(resultSet2.getString(i));
				i++;
				}
			resultSetAsArrayList.remove("id"); //necessary until I figure out how not to select these
			resultSetAsArrayList.remove("owner_type");
			resultSetAsArrayList.remove("customer_id");
			resultSetAsArrayList.remove("active");
			resultSetAsArrayList.remove("id");
			if (!Main.hikariEnabled) connection.close(); // necessary for the JDBC
		}
	
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSetAsArrayList;
	}

	@Override
	public boolean changePassword() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isAdmin(int userID) {
		// TODO Auto-generated method stub
		return false;
	}


	public ArrayList<String> getUsernameList() {
			String query = "Select username from account where id > 0";
			ArrayList<String> allOfTheUsers = new ArrayList<>();
			try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);){
				logger.info("Connected to database");
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					allOfTheUsers.add(resultSet.getObject(1) + "");
					logger.info("List of usernames retrieved");
				}
				if (!Main.hikariEnabled) connection.close(); // necessary for the JDBC
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return allOfTheUsers;
		}
}

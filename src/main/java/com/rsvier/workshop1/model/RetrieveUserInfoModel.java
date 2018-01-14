package com.rsvier.workshop1.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import com.rsvier.workshop1.database.DataSource;
import com.zaxxer.hikari.*;

public class RetrieveUserInfoModel extends Model {
	
/*	public void test() {
		try {
			URL url = new URL("C:\\Users\\Onne\\git\\Workshop1\\pom.xml");
			Document document = new XMLReader().parse(url);
			document.
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} */
	
	public int retrieveUserId(String username) {
		username = "'" + username + "'";
		String query = "select id from account where username = "
				+ username + ";";
		ArrayList<String> queryResult = databaseQuery(query);
		return Integer.parseInt(queryResult.get(0));
	}

	public boolean retrieveAdminStatus(int userID) {
		String query = "select owner_type from account where id = "
				+ userID + ";";
		ArrayList<String> queryResult = databaseQuery(query);
		if (queryResult.get(0).equals("ADMIN")) return true;
		return false;
	}
	
	public boolean login(String username, String password) {
		username = "'" + username + "'";
		String query = "Select password from account where username = "
				+ username + ";";
		ArrayList<String> queryResult = databaseQuery(query);
		if (queryResult.isEmpty()) return false;
		if (queryResult.get(0).equals(password)) return true;
		return false;
	}
	
	public ArrayList<String> showAllUsernames() {
		String query = "Select username from account where id > 0";
		ArrayList<String> allOfTheUsers = new ArrayList<>();
		try (Connection con = DataSource.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				allOfTheUsers.add(resultSet.getObject(1) + "");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return allOfTheUsers;
	}
	
	public ArrayList<String> showAllUsers() {
		String query = "Select * from account where id > 0";
		ArrayList<String> allOfTheUsers = new ArrayList<>();
		try (Connection con = DataSource.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				allOfTheUsers.add(resultSet.getObject(1) + " ");
				allOfTheUsers.add(resultSet.getObject(2) + " ");
				allOfTheUsers.add(resultSet.getObject(3) + " ");
				allOfTheUsers.add(resultSet.getObject(4) + " ");
				allOfTheUsers.add(resultSet.getObject(5) + " ");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return allOfTheUsers;
	}
	
	private ArrayList<String> databaseQuery(String query) {
		ArrayList<String> resultSetAsArrayList = new ArrayList<>();
		try (Connection con = DataSource.getConnection();
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				int i = 1;
				resultSetAsArrayList.add(resultSet.getString(i));
				i++;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSetAsArrayList;
	}
}

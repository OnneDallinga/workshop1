package com.rsvier.workshop1.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import com.rsvier.workshop1.controller.Main;
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
	

	/*public void showTable() { //temporary
		String query = "select * from customer where id > 0";
		try (Connection connection = DataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*public ArrayList<String> showAllUsers() {
		String query = "Select * from account where id > 0";
		ArrayList<String> allOfTheUsers = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				allOfTheUsers.add(resultSet.getObject(1) + " ");
				allOfTheUsers.add(resultSet.getObject(2) + " ");
				allOfTheUsers.add(resultSet.getObject(3) + " ");
				allOfTheUsers.add(resultSet.getObject(4) + " ");
				allOfTheUsers.add(resultSet.getObject(5) + " ");
			}
			if (!Main.hikariEnabled) connection.close(); // necessary for the JDBC
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return allOfTheUsers;
	}*/
	
	/*private ArrayList<String> databaseQuery(String query) {
		ArrayList<String> resultSetAsArrayList = new ArrayList<>();
		try (Connection connection = DataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();) {
			while (resultSet.next()) {
				int i = 1;
				resultSetAsArrayList.add(resultSet.getString(i));
				i++;
			}
			if (!Main.hikariEnabled) connection.close(); // necessary for the JDBC
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSetAsArrayList;
	}*/

	/*public ArrayList<String> retrieveAccountProperties() { // retrieves the information we need from the customer from the tables
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
	}*/
}

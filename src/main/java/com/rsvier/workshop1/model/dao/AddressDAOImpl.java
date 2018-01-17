package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;
import java.sql.*;
import com.rsvier.workshop1.model.Address;
import com.rsvier.workshop1.database.DataSource;

public class AddressDAOImpl implements AddressDAO {

	private Connection connection;
	private String query;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	@Override
	public int createAddress(Address address) {
		int newAddressId = 0;
		statement = null;
		query = "INSERT INTO address (postal_code, street_name, city, house_number, house_number_addition," +
				"address_type, customer_id) VALUES (?,?,?,?,?,?,?);";
		try {
			connection = DataSource.getConnection();
		    statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    statement.setString(2, address.getPostalCode());
		    statement.setString(3, address.getStreet());
		    statement.setString(4, address.getCity());
		    statement.setInt(5, address.getHouseNumber());
		    statement.setString(6, address.getHouseNumberAddition());
		    statement.setString(7, address.getAddressType());
		    statement.setInt(8, address.getCustomerId());
		    statement.executeUpdate();
		    try {
		      resultSet = statement.getGeneratedKeys();
		      if (resultSet.next()) {
		    	newAddressId = resultSet.getInt(1);
		        address.setAddressId(newAddressId);
		      }           
		    } catch (SQLException e) {
		      System.out.println("Creating new address failed.");
		    }
		 } catch (SQLException e) {
		      e.printStackTrace();
		 } finally {
		   try {
		    connection.close();
		    } catch (SQLException e) {
		    }
		 }
		    return newAddressId;
		
	}

	@Override
	public ArrayList<Address> findAllAddresses() {
		statement = null;
		ArrayList<Address> list = new ArrayList<Address>();
		query = "SELECT * FROM address;";
		try {
		  connection = DataSource.getConnection();
		  statement = connection.prepareStatement(query);
		  resultSet = statement.executeQuery();
			      
		  while(resultSet.next()) {
		    Address address = new Address();
			address.setAddressId(resultSet.getInt(1));
			address.setPostalCode(resultSet.getString(2));
			address.setStreet(resultSet.getString(3));
			address.setCity(resultSet.getString(4));
			address.setHouseNumber(resultSet.getInt(5));
			address.setHouseNumberAddition(resultSet.getString(6));
			address.setAddressType(resultSet.getString(7));
			address.setCustomerId(resultSet.getInt(8));
			list.add(address);
		  }
		  resultSet.close();
		} catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	    	connection.close();
		  } catch (SQLException e) {
		  }
	    }
		return list;
	}

	@Override
	public Address findAddress(int addressId) {
		statement = null;
		Address foundAddress = new Address();
		query = "SELECT * FROM address WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setObject(1, addressId);
	      resultSet = statement.executeQuery();
	      
	      if(resultSet.next()) {
			foundAddress.setAddressId(resultSet.getInt(1));
			foundAddress.setPostalCode(resultSet.getString(2));
			foundAddress.setStreet(resultSet.getString(3));
			foundAddress.setCity(resultSet.getString(4));
			foundAddress.setHouseNumber(resultSet.getInt(5));
			foundAddress.setHouseNumberAddition(resultSet.getString(6));
			foundAddress.setAddressType(resultSet.getString(7));
			foundAddress.setCustomerId(resultSet.getInt(8));
		  }
		} catch (SQLException e) {
		  e.printStackTrace();
		} finally {
		  try {
		    connection.close();
		  } catch (SQLException e) {
		  }
		}
	    return foundAddress;
	}

	@Override
	public void updateAddress(Address address) {
		statement = null;
	    query = "UPDATE address SET postal_code = ?, street_name = ?, city = ?, house_number = ?" +
	        "house_number_addition = ?, address_type = ?, customer_id = ? WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setString(1, address.getPostalCode());
	      statement.setString(2, address.getStreet());
	      statement.setString(3, address.getCity());
	      statement.setInt(4, address.getHouseNumber());
	      statement.setString(5, address.getHouseNumberAddition());
	      statement.setString(6, address.getAddressType());
	      statement.setInt(7, address.getCustomerId());
	      statement.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        connection.close();
	      } catch (SQLException e) {
	      }
	    }
	}

	@Override
	public void deleteAddress(Address address) {
		statement = null;
	    query = "DELETE FROM address WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setInt(1, address.getAddressId());
	      statement.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        connection.close();
	      } catch (SQLException e) {
	      }
	    }
	}
}

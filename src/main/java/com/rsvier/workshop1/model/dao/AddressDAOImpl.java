package com.rsvier.workshop1.model.dao;

import com.rsvier.workshop1.model.Address;
import com.rsvier.workshop1.database.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AddressDAOImpl implements AddressDAO {

	private String query;	
	private Logger logger = Logger.getLogger(AddressDAOImpl.class.getName());

	@Override
	public int createAddress(Address address) {
		int newAddressId = 0;
		query = "INSERT INTO address (postal_code, street_name, city, house_number, house_number_addition," +
				"address_type, customer_id) VALUES (?,?,?,?,?,?,?);";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = stmt.getGeneratedKeys();) {
			logger.info("Connected to database.");
			stmt.setString(2, address.getPostalCode());
			stmt.setString(3, address.getStreet());
			stmt.setString(4, address.getCity());
			stmt.setInt(5, address.getHouseNumber());
			stmt.setString(6, address.getHouseNumberAddition());
			stmt.setString(7, address.getAddressType());
			stmt.setInt(8, address.getCustomerId());
			stmt.executeUpdate();
			try {
				if (rs.next()) {
					newAddressId = rs.getInt(1);
					address.setAddressId(newAddressId);
					logger.info("Succesfully added new address.");
				}           
			} catch (SQLException e) {
				logger.info("Creating new address failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return newAddressId;
	}

	@Override
	public List<Address> findAllAddresses() {
		List<Address> list = new ArrayList<Address>();
		query = "SELECT * FROM address;";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");
			while(rs.next()) {
				Address address = new Address();
				address.setAddressId(rs.getInt(1));
				address.setPostalCode(rs.getString(2));
				address.setStreet(rs.getString(3));
				address.setCity(rs.getString(4));
				address.setHouseNumber(rs.getInt(5));
				address.setHouseNumberAddition(rs.getString(6));
				address.setAddressType(rs.getString(7));
				address.setCustomerId(rs.getInt(8));
				list.add(address);
			}
			logger.info("Total addresses:" + rs.getRow());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public Address findAddress(int addressId) {
		Address foundAddress = new Address();
		query = "SELECT * FROM address WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");
			stmt.setObject(1, addressId);
			if(rs.next()) {
				foundAddress.setAddressId(rs.getInt(1));
				foundAddress.setPostalCode(rs.getString(2));
				foundAddress.setStreet(rs.getString(3));
				foundAddress.setCity(rs.getString(4));
				foundAddress.setHouseNumber(rs.getInt(5));
				foundAddress.setHouseNumberAddition(rs.getString(6));
				foundAddress.setAddressType(rs.getString(7));
				foundAddress.setCustomerId(rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return foundAddress;
	}

	@Override
	public void updateAddress(Address address) {
		query = "UPDATE address SET postal_code = ?, street_name = ?, city = ?, house_number = ?" +
				"house_number_addition = ?, address_type = ?, customer_id = ? WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			stmt.setString(1, address.getPostalCode());
			stmt.setString(2, address.getStreet());
			stmt.setString(3, address.getCity());
			stmt.setInt(4, address.getHouseNumber());
			stmt.setString(5, address.getHouseNumberAddition());
			stmt.setString(6, address.getAddressType());
			stmt.setInt(7, address.getCustomerId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void deleteAddress(Address address) {
		query = "DELETE FROM address WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");
			stmt.setInt(1, address.getAddressId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}

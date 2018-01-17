package com.rsvier.workshop1.model.dao;

import java.sql.*;
import java.util.ArrayList;
import com.rsvier.workshop1.model.Customer;
import com.rsvier.workshop1.database.DataSource;

import com.rsvier.workshop1.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public void createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomer(long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(long customerId) {
		// TODO Auto-generated method stub
		
	}
		
	private Connection connection;
	private String query;
	private PreparedStatement statement;
	private ResultSet resultSet;

	@Override
	public int createCustomer(Customer customer) {
		int newCustomerId = 0;
		statement = null;
		query = "INSERT INTO customer (first_name, last_name, last_name_preposition, email, phone_number) VALUES (?,?,?,?,?);";
		try {
			connection = DataSource.getConnection();
		    statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    statement.setString(2, customer.getFirstName());
		    statement.setString(3, customer.getLastName());
		    statement.setString(4, customer.getLastNamePreposition());
		    statement.setString(5, customer.getEmail());
		    statement.setString(6, customer.getPhoneNumber());
		    statement.executeUpdate();
		    try {
		      resultSet = statement.getGeneratedKeys();
		      if (resultSet.next()) {
		    	newCustomerId = resultSet.getInt(1);
		        customer.setCustomerId(newCustomerId);
		      }           
		    } catch (SQLException e) {
		      System.out.println("Creating new user failed.");
		    }
		 } catch (SQLException e) {
		      e.printStackTrace();
		 } finally {
		   try {
		    connection.close();
		    } catch (SQLException e) {
		    }
		 }
		    return newCustomerId;
	}

	@Override
	public ArrayList<Customer> findAllCustomers() {
		statement = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		query = "SELECT * FROM customer;";
		try {
		  connection = DataSource.getConnection();
		  statement = connection.prepareStatement(query);
		  resultSet = statement.executeQuery();
			      
		  while(resultSet.next()) {
		    Customer customer = new Customer();
			customer.setCustomerId(resultSet.getInt(1));
			customer.setFirstName(resultSet.getString(2));
			customer.setLastName(resultSet.getString(3));
			customer.setLastNamePreposition(resultSet.getString(4));
			customer.setEmail(resultSet.getString(5));
			customer.setPhoneNumber(resultSet.getString(6));
			list.add(customer);
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
	public Customer findCustomerById(int customerId) {
		statement = null;
		Customer foundCustomer = new Customer();
		query = "SELECT * FROM customer WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setObject(1, customerId);
	      resultSet = statement.executeQuery();
	      
	      if(resultSet.next()) {
		    foundCustomer.setCustomerId(resultSet.getInt(1));
		    foundCustomer.setFirstName(resultSet.getString(2));
		    foundCustomer.setLastName(resultSet.getString(3));
		    foundCustomer.setLastNamePreposition(resultSet.getString(4));
		    foundCustomer.setEmail(resultSet.getString(5));
		    foundCustomer.setPhoneNumber(resultSet.getString(6));
		  }
		} catch (SQLException e) {
		  e.printStackTrace();
		} finally {
		  try {
		    connection.close();
		  } catch (SQLException e) {
		  }
		}
	    return foundCustomer;
	}

	@Override
	public Customer findCustomerByFirstName(String firstName) {
		statement = null;
		Customer foundCustomer = new Customer();
		query = "SELECT * FROM customer WHERE first_name=?";
		try {
			connection = DataSource.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, firstName);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
			    foundCustomer.setCustomerId(resultSet.getInt(1));
			    foundCustomer.setFirstName(resultSet.getString(2));
			    foundCustomer.setLastName(resultSet.getString(3));
			    foundCustomer.setLastNamePreposition(resultSet.getString(4));
			    foundCustomer.setEmail(resultSet.getString(5));
			    foundCustomer.setPhoneNumber(resultSet.getString(6));
			}
		} catch (SQLException e) {
		  e.printStackTrace();
		} finally {
		  try {
			  connection.close();
		  } catch (SQLException e) {
		  }
		}
		return foundCustomer;
	}

	@Override
	public Customer findCustomerByLastName(String lastName) {
		statement = null;
		Customer foundCustomer = new Customer();
		query = "SELECT * FROM customer WHERE last_name=?";
		try {
			connection = DataSource.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, lastName);
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
			    foundCustomer.setCustomerId(resultSet.getInt(1));
			    foundCustomer.setFirstName(resultSet.getString(2));
			    foundCustomer.setLastName(resultSet.getString(3));
			    foundCustomer.setLastNamePreposition(resultSet.getString(4));
			    foundCustomer.setEmail(resultSet.getString(5));
			    foundCustomer.setPhoneNumber(resultSet.getString(6));
			}
		} catch (SQLException e) {
		  e.printStackTrace();
		} finally {
		  try {
			  connection.close();
		  } catch (SQLException e) {
		  }
		}
		return foundCustomer;
	}

	@Override
	public void updateCustomer(Customer customer) {
		statement = null;
	    query = "UPDATE customer SET firstName = ?, lastName = ?," +
	        "email = ? WHERE customerId=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setInt(1, customer.getCustomerId());
	      statement.setString(2, customer.getFirstName());
	      statement.setString(3, customer.getLastName());
	      statement.setString(4, customer.getLastNamePreposition());
	      statement.setString(5, customer.getEmail());
	      statement.setString(6, customer.getPhoneNumber());
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

	/* Full customer deletion includes deletion of all orders with the associated customer */
	@Override
	public void deleteCustomer(Customer customer) {
		statement = null;
	    query = "SELECT * FROM order WHERE customer_customerID=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setInt(1, customer.getCustomerId());
	      resultSet = statement.executeQuery();
	      if (!resultSet.next()) {
	        query = "DELETE FROM customer WHERE id=?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, customer.getCustomerId());
	        statement.executeUpdate();
	      } else {
	        System.out.println("Client has active orders in place, cannot delete.");
	      }
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

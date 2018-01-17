package com.rsvier.workshop1.model.dao;

import java.sql.*;
import java.util.ArrayList;
import com.rsvier.workshop1.database.DataSource;
import com.rsvier.workshop1.model.Product;

public class ProductsDAOImpl implements ProductDAO {
	
	private Connection connection;
	private String query;
	private PreparedStatement statement;
	private ResultSet resultSet;

	@Override
	public int createProduct(Product product) {
		int newProductId = 0;
		statement = null;
		query = "INSERT INTO product (name, price, stock_quantity, produced_year, alcohol_percentage) VALUES (?,?,?,?,?);";
		try {
			connection = DataSource.getConnection();
		    statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    statement.setString(1, product.getProductName());
		    statement.setBigDecimal(2, product.getPrice());
		    statement.setInt(3, product.getStockQuantity());
		    statement.setInt(4, product.getProducedYear());
		    statement.setDouble(5, product.getAlcoholPercentage());
		    statement.executeUpdate();
		    try {
		      resultSet = statement.getGeneratedKeys();
		      if (resultSet.next()) {
		    	  newProductId = resultSet.getInt(1);
		    	  product.setProductId(newProductId);
		      }           
		    } catch (SQLException e) {
		      System.out.println("Creating new product failed.");
		    }
		 } catch (SQLException e) {
		      e.printStackTrace();
		 } finally {
		   try {
		    connection.close();
		    } catch (SQLException e) {
		    }
		 }
		    return newProductId;
	}

	@Override
	public ArrayList<Product> findAllProducts() {
		statement = null;
		ArrayList<Product> list = new ArrayList<Product>();
		query = "SELECT * FROM product;";
		try {
		  connection = DataSource.getConnection();
		  statement = connection.prepareStatement(query);
		  resultSet = statement.executeQuery();
			      
		  while(resultSet.next()) {
		    Product product = new Product();
		    product.setProductId(resultSet.getInt(1));
		    product.setProductName(resultSet.getString(2));
		    product.setPrice(resultSet.getBigDecimal(3));
		    product.setStockQuantity(resultSet.getInt(4));
		    product.setProducedYear(resultSet.getInt(5));
		    product.setAlcoholPercentage(resultSet.getDouble(6));
			list.add(product);
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
	public Product findProductById(int productId) {
		statement = null;
		Product foundProduct = new Product();
		query = "SELECT * FROM product WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setObject(1, productId);
	      resultSet = statement.executeQuery();
	      
	      if(resultSet.next()) {
	    	foundProduct.setProductId(resultSet.getInt(1));
	    	foundProduct.setProductName(resultSet.getString(2));
	    	foundProduct.setPrice(resultSet.getBigDecimal(3));
	    	foundProduct.setStockQuantity(resultSet.getInt(4));
		    foundProduct.setProducedYear(resultSet.getInt(5));
		    foundProduct.setAlcoholPercentage(resultSet.getDouble(6));
		  }
		} catch (SQLException e) {
		  e.printStackTrace();
		} finally {
		  try {
		    connection.close();
		  } catch (SQLException e) {
		  }
		}
	    return foundProduct;
	}

	@Override
	public void updateProduct(Product product) {
		statement = null;
	    query = "UPDATE product SET name = ?, price = ?, stock_quantity = ?" + 
		"produced_year = ?, alcohol_percentage = ? WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setString(1, product.getProductName());
	      statement.setBigDecimal(2, product.getPrice());
	      statement.setInt(3, product.getStockQuantity());
	      statement.setInt(4, product.getProductId());
	      statement.setInt(5, product.getProducedYear());
	      statement.setDouble(6,  product.getAlcoholPercentage());
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
	public void deleteProduct(Product product) {
		statement = null;
	    query = "SELECT * FROM product WHERE id=?";
	    try {
	      connection = DataSource.getConnection();
	      statement = connection.prepareStatement(query);
	      statement.setInt(1, product.getProductId());
	      resultSet = statement.executeQuery();
	      if (!resultSet.next()) {
	        query = "DELETE FROM product WHERE id=?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, product.getProductId());
	        statement.executeUpdate();
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

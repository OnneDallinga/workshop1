package com.rsvier.workshop1.model.dao;

import com.rsvier.workshop1.model.Product;
import com.rsvier.workshop1.database.DataSource;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ProductDAOImpl implements ProductDAO {

	private String query;
	private Logger logger = Logger.getLogger(ProductDAOImpl.class.getName());

	@Override
	public int createProduct(Product newProduct) {
		int newProductId = 0;
		query = "INSERT INTO product (name, price, stock_quantity, produced_year, country," +
				"grapeVariety, alcohol_percentage) VALUES (?,?,?,?,?,?,?);";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
			logger.info("Connected to database.");
			stmt.setString(1, newProduct.getProductName());
			stmt.setBigDecimal(2, newProduct.getPrice());
			stmt.setInt(3, newProduct.getStockQuantity());
			stmt.setInt(4, newProduct.getProducedYear());
			stmt.setString(5, newProduct.getCountry());
			stmt.setString(6,  newProduct.getGrapeVariety());
			stmt.setDouble(7, newProduct.getAlcoholPercentage());
			stmt.executeUpdate();
			try (ResultSet rs = stmt.getGeneratedKeys();) {
				if (rs.next()) {
					newProductId = rs.getInt(1);
					newProduct.setProductId(newProductId);
				}           
			} catch (SQLException e) {
				logger.info("Creating new product failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		logger.info("Succesfully added new newProduct.");
		return newProductId;
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> allProducts = new ArrayList<Product>();
		query = "SELECT * FROM product;";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");	    
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getBigDecimal(3));
				product.setStockQuantity(rs.getInt(4));
				product.setProducedYear(rs.getInt(5));
				product.setCountry(rs.getString(6));
				product.setGrapeVariety(rs.getString(7));
				product.setAlcoholPercentage(rs.getDouble(8));
				allProducts.add(product);
			}
			logger.info("Total products:" + rs.getRow());
		} catch (SQLException e) {
			logger.info("Could not retrieve products.");
			e.printStackTrace();
		} 
		return allProducts;
	}

	@Override
	public Product findProductById(int productId) {
		Product foundProduct = new Product();
		query = "SELECT * FROM product WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");	    
			stmt.setInt(1, productId);
			try (ResultSet rs = stmt.executeQuery();) {
				if(rs.next()) {
					foundProduct.setProductId(rs.getInt(1));
					foundProduct.setProductName(rs.getString(2));
					foundProduct.setPrice(rs.getBigDecimal(3));
					foundProduct.setStockQuantity(rs.getInt(4));
					foundProduct.setProducedYear(rs.getInt(5));
					foundProduct.setCountry(rs.getString(6));
					foundProduct.setGrapeVariety(rs.getString(7));
					foundProduct.setAlcoholPercentage(rs.getDouble(8));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			logger.info("No product found.");
			e.printStackTrace();
		} 
		return foundProduct;
	}
	
	@Override
	public Product findProductByName(String name) {
		Product foundProduct = new Product();
		query = "SELECT * FROM product WHERE name=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");	    
			stmt.setString(1, name);
			try (ResultSet rs = stmt.executeQuery();) {
				if(rs.next()) {
					foundProduct.setProductId(rs.getInt(1));
					foundProduct.setProductName(rs.getString(2));
					foundProduct.setPrice(rs.getBigDecimal(3));
					foundProduct.setStockQuantity(rs.getInt(4));
					foundProduct.setProducedYear(rs.getInt(5));
					foundProduct.setCountry(rs.getString(6));
					foundProduct.setGrapeVariety(rs.getString(7));
					foundProduct.setAlcoholPercentage(rs.getDouble(8));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			logger.info("No product found.");
			e.printStackTrace();
		} 
		return foundProduct;
	}

	@Override
	public void updateProduct(Product product) {
		query = "UPDATE product SET name = ?, price = ?, stock_quantity = ?" + 
				"produced_year = ?, country = ?, grape_variety = ?," + 
				"alcohol_percentage = ? WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");	    
			stmt.setString(1, product.getProductName());
			stmt.setBigDecimal(2, product.getPrice());
			stmt.setInt(3, product.getStockQuantity());
			stmt.setInt(4, product.getProducedYear());
			stmt.setString(5,  product.getCountry());
			stmt.setString(6, product.getGrapeVariety());
			stmt.setDouble(7, product.getAlcoholPercentage());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.info("Updating product failed.");
			e.printStackTrace();
		} 
	}

	@Override
	public void deleteProduct(Product product) {
		query = "DELETE FROM product WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);) {
			logger.info("Connected to database.");	    
			stmt.setInt(1, product.getProductId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.info("Deleting product failed.");
			e.printStackTrace();
		} 
	}
}

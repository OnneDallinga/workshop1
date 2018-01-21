package com.rsvier.workshop1.model.dao;

import com.rsvier.workshop1.model.Product;
import com.rsvier.workshop1.database.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ProductDAOImpl implements ProductDAO {

	private String query;
	private Logger logger = Logger.getLogger(ProductDAOImpl.class.getName());

	@Override
	public int createProduct(Product product) {
		int newProductId = 0;
		query = "INSERT INTO product (name, price, stock_quantity, produced_year, country," +
				"grapeVariety, alcohol_percentage) VALUES (?,?,?,?,?,?,?);";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = stmt.getGeneratedKeys();) {
			logger.info("Connected to database.");
			stmt.setString(1, product.getProductName());
			stmt.setBigDecimal(2, product.getPrice());
			stmt.setInt(3, product.getStockQuantity());
			stmt.setInt(4, product.getProducedYear());
			stmt.setString(5, product.getCountry());
			stmt.setString(6,  product.getGrapeVariety());
			stmt.setDouble(7, product.getAlcoholPercentage());
			stmt.executeUpdate();
			try {
				if (rs.next()) {
					newProductId = rs.getInt(1);
					product.setProductId(newProductId);
					logger.info("Succesfully added new product.");
				}           
			} catch (SQLException e) {
				logger.info("Creating new product failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return newProductId;
	}

	@Override
	public ArrayList<Product> findAllProducts() {
		ArrayList<Product> list = new ArrayList<Product>();
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
				list.add(product);
			}
			logger.info("Total products:" + rs.getRow());
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public Product findProductById(int productId) {
		Product foundProduct = new Product();
		query = "SELECT * FROM product WHERE id=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");	    
			stmt.setObject(1, productId);
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
		return foundProduct;
	}

	@Override
	public Product findProductByCountry(String country) {
		Product foundProduct = new Product();
		query = "SELECT * FROM product WHERE country=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");	    
			stmt.setObject(1, country);
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
		return foundProduct;
	}

	@Override
	public Product findProductByGrapeVariety(String grapeVariety) {
		Product foundProduct = new Product();
		query = "SELECT * FROM product WHERE grape_variety=?";
		try (Connection conn = DataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();) {
			logger.info("Connected to database.");	    
			stmt.setObject(1, grapeVariety);
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
			e.printStackTrace();
		} 
	}
}

package com.rsvier.workshop1.model.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.sql.*;
import com.rsvier.workshop1.database.DataSource;
import com.rsvier.workshop1.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

public class ProductDAOImplTest {
	
	ProductDAOImpl productDao = new ProductDAOImpl();
	Logger logger = Logger.getLogger(ProductDAOImplTest.class.getName());
	
	@BeforeEach
	void setUp() {
		logger.info("Setting up database..");
		try {
			Connection conn = DataSource.getConnection();
			// Set up two product rows for testing
			String query = "INSERT INTO product (name, price, stock_quantity, produced_year," +
						   "country, grapeVariety, alcohol_percentage)" + 
						   "VALUES ('Avec Toutatis', 10.0, 10, 2017, 'France', 'Malbec', 7.2)";
			Statement stmt = conn.createStatement();
			stmt.addBatch(query);
			String query2 = "INSERT INTO product (name, price, stock_quantity, produced_year," +
							"country, grapeVariety, alcohol_percentage)" +
							"VALUES ('El Rioja Diablo', 8.4, 42, 2014, 'Spain', 'Rioja', 6.66)";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			fail("Error instantiating database table: " + ex.getMessage());
		}
	}
	
	@AfterEach
	void tearDown() {
		logger.info("Cleaning up database..");
		try {
			Connection conn = DataSource.getConnection();
			Statement stmt = conn.createStatement();
			String query = "DELETE FROM product";
			stmt.addBatch(query);
			String query2 = "ALTER TABLE product AUTO_INCREMENT = 1";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	// Currently failing: returns ID = 0
	@Test
	void canCreateProduct() {
		Product product = new Product();
		product.setProductName("Chateau de Degoutant");
		product.setPrice(new BigDecimal("8"));
		product.setStockQuantity(5);

		// The method returns the created product's id which should be 3 given setUp()
		int createdProductId = productDao.createProduct(product);

		assertEquals(3, createdProductId);
	}
	
	@Test
	void canFindAllProducts() {
		List<Product> products = productDao.findAllProducts();

		assertAll(
		() -> assertEquals(2, products.size()),
		() -> assertNotNull(products.get(0).getProductName()),
		() -> assertNotNull(products.get(1).getProductName())
		);
	}
	
	// Currently failing, returned id = 0
	@Test
	void canFindProductById() {
		Product product = productDao.findProductById(2);

		assertEquals(2, product.getProductId());
	}
	
	// Currently failing: returned product name is null
	@Test
	void canFindProductByName() {
		Product product = productDao.findProductByName("El Rioja Diablo");
		
		assertEquals("El Rioja Diablo", product.getProductName());
	}
	
	@Test
	void canUpdateProduct() {
		// Goal: change price of 1st product from 10 to 9
		Product productToUpdate = new Product(1, "Avec Toutatis", new BigDecimal("9"), 10);
		
		productDao.updateProduct(productToUpdate);
		
		try {
			Connection conn = DataSource.getConnection();
			String query = "SELECT * FROM product WHERE id=1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			assertEquals(new BigDecimal("9"), rs.getBigDecimal(3));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	// Throws an exception due to the logic of deleting product with ID 2 and then trying to retrieve it from DB
	void canDeleteProduct() throws Exception {
		Product product = new Product();
		product.setProductId(1);

		productDao.deleteProduct(product);

		try {
			Connection conn = DataSource.getConnection();
			String query = "SELECT * FROM product WHERE id=1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}

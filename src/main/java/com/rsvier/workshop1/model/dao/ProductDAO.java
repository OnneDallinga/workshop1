package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Product;

public interface ProductDAO {

	//Create
	public int createProduct(Product product);
	
	// Read
	public ArrayList<Product> findAllProducts();
	public Product findProductById(int productId);
	public Product findProductByCountry(String country);
	public Product findProductByGrapeVariety(String grapeVariety);
	
	// Update
	public void updateProduct(Product product);
	
	// Delete
	public void deleteProduct(Product product);
	
}

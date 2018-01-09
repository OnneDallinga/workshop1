package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Product;

public interface ProductDAO {

	//Create
	public int newProduct(Product product);
	
	// Read
	public ArrayList<Product> findAllProducts();
	public Product findProduct(long productId);
	
	// Update
	public void updateProduct(long productId);
	
	// Delete
	public void deleteProduct(long productId);
	
}

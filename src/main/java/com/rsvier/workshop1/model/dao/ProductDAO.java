package com.rsvier.workshop1.model.dao;

import java.util.List;

import com.rsvier.workshop1.model.Product;

public interface ProductDAO {

	//Create
	public int createProduct(Product product);
	
	// Read
	public List<Product> findAllProducts();
	public Product findProductById(int productId);
	public Product findProductByName(String name);
	
	// Update
	public void updateProduct(Product product);
	
	// Delete
	public void deleteProduct(Product product);
	
}

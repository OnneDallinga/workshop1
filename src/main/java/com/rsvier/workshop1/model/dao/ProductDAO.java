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
	public boolean isProductStoredWithId(int productId);
	
	// Update
	public boolean updateProduct(Product product);
	
	// Delete
	public boolean deleteProduct(Product product);
	
}

package com.rsvier.workshop1.model;

import java.util.List;

public interface ProductDAO {

	public List<Product> getAllProducts();
	public Product getProduct(long productId);
	public void updateProduct(Product product);
	public void deleteProduct(Product product);
	
}

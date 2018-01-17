package com.rsvier.workshop1.model;

import java.math.BigDecimal;

public class Product {
	
	private int productId;
	private String productName;
	private BigDecimal price;
	private boolean inStock;
	private int stockQuantity;
	private int producedYear;
	private double alcoholPercentage;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
		if (stockQuantity == 0)
			this.setInStock(false);
		else if (stockQuantity > 0)
			this.setInStock(true);
	}
	
	public int getProducedYear() {
		return producedYear;
	}
	
	public void setProducedYear(int producedYear) {
		this.producedYear = producedYear;
	}
	
	public double getAlcoholPercentage() {
		return alcoholPercentage;
	}
	
	public void setAlcoholPercentage(double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price +
			   ", inStock=" + inStock + ", stockQuantity=" + stockQuantity + ", producedYear=" + producedYear +
			   ", alcoholPercentage=" + alcoholPercentage + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return productId == ((Product)obj).getProductId();
	}
}

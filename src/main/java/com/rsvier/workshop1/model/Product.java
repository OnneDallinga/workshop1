package com.rsvier.workshop1.model;

import java.math.BigDecimal;

public class Product {

	private int productId;
	private String productName;
	private BigDecimal price;
	private int stockQuantity;
	private int producedYear;
	private String country;
	private String grapeVariety;
	private double alcoholPercentage;

	public Product() {
	}

	// Constructor with basic params
	public Product(int productId,
			String productName,
			BigDecimal price,
			int stockQuantity) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	// Constructor with all params
	public Product(int productId,
			String productName,
			BigDecimal price,
			int stockQuantity,
			int producedYear,
			String country,
			String grapeVariety,
			double alcoholPercentage) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.producedYear = producedYear;
		this.country = country;
		this.grapeVariety = grapeVariety;
		this.alcoholPercentage = alcoholPercentage;
	}

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

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGrapeVariety() {
		return grapeVariety;
	}

	public void setGrapeVariety(String grapeVariety) {
		this.grapeVariety = grapeVariety;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price +
				", stockQuantity=" + stockQuantity + ", producedYear=" + producedYear + ", country=" + country +
				", grapeVariety=" + grapeVariety + ", alcoholPercentage=" + alcoholPercentage + "]";
	}
}

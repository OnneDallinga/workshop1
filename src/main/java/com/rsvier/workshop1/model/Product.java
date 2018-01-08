package com.rsvier.workshop1.model;

import java.math.BigDecimal;

public class Product {
	
	private long productId;
	private String productName;
	private String producerName;
	private BigDecimal price;
	private boolean inStock;
	private int stockQuantity;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
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
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", producerName=" + producerName
				+ ", price=" + price + ", inStock=" + inStock + ", stockQuantity=" + stockQuantity + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return productId == ((Product)obj).getProductId();
	}
}

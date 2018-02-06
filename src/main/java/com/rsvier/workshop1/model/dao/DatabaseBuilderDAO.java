package com.rsvier.workshop1.model.dao;

public interface DatabaseBuilderDAO {
	
	// Read
	public abstract boolean isDatabaseInitialized();
	
	// Create
	public abstract boolean initializeDatabase();

}

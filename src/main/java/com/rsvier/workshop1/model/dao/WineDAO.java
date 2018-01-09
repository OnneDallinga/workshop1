package com.rsvier.workshop1.model.dao;

import java.util.ArrayList;

import com.rsvier.workshop1.model.Wine;

public interface WineDAO {
	
	// Create
	public void newWine(Wine wine);
	
	// Read
	public ArrayList<Wine> findAllWines();
	public Wine findWine(long wineId);
	
	// Update
	public void updateWine(Wine wine);
	
	// Delete
	public void deleteWine(Wine wine);
	public void deleteWine(long wineId);

}

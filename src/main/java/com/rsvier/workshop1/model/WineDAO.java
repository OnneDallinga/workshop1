package model;

import java.util.List;

public interface WineDAO {
	
	public List<Wine> getAllWines();
	public Wine getWine(long wineId);
	public void updateWine(Wine wine);
	public void deleteWine(Wine wine);

}

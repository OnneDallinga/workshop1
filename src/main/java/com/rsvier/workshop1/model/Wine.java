package model;

import java.util.Arrays;

public class Wine extends Product {
	
	private long wineId;
	private int producedYear;
	private String region;
	private String[] grapeVarieties;
	private double alcoholPercentage;
	/* Stub for implementing a Wine-rating API at a later date
	private double rating; */
	
	public long getwineId() {
		return wineId;
	}
	
	public void setWineId(long wineId) {
		this.wineId = wineId;
	}

	public int getProducedYear() {
		return producedYear;
	}

	public void setProducedYear(int producedYear) {
		this.producedYear = producedYear;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String[] getGrapeVarieties() {
		return grapeVarieties;
	}

	public void setGrapeVarieties(String[] grapeVarieties) {
		this.grapeVarieties = grapeVarieties;
	}
	
	public double getAlcoholPercentage() {
		return alcoholPercentage;
	}

	public void setAlcoholPercentage(double alcoholPercentage) {
		this.alcoholPercentage = alcoholPercentage;
	}

	@Override
	public String toString() {
		return "Wine [wineId=" + wineId + ", productName=" + super.getProductName() + ", producerName" + super.getProducerName() +
				", producedYear=" + producedYear + ", region=" + region + ", grapeVarieties=" + Arrays.toString(grapeVarieties) +
				", alcoholPercentage=" + alcoholPercentage + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return wineId == ((Wine)obj).getwineId();
	}
}

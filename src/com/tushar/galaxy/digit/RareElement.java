package com.tushar.galaxy.digit;

public enum RareElement {

	SILVER(0.0), GOLD(0.0), IRON(0.0);
	
	double value;
	
	RareElement(double value) {
		this.value = value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	
}

package com.tushar.galaxy.digit;

public enum IntergalacticDigit {

	GLOB(0), PROK(0), PISH(0), TEGJ(0);
	
	int value;
	
	IntergalacticDigit(int value) {
		this.value = value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}

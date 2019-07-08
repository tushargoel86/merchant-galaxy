package com.tushar.galaxy.service;

import java.util.Arrays;

import com.tushar.galaxy.digit.RareElement;
import com.tushar.galaxy.digit.RomanDigit;
import com.tushar.galaxy.utility.UtilityInterface;

public class MerchantService {

	public void updateRareElementValues() {
		for (String line : UtilityInterface.CONVERSION_MATRIX) {
			line = line.toUpperCase();
			String []value = line.split("\\s+");
			
			// sum of 2 intergalactic unit
			String romanDigit = UtilityInterface.INTERGALACTICE_To_ROMAN.get(value[0]) 
									+ UtilityInterface.INTERGALACTICE_To_ROMAN.get(value[1]);
			int intergalacticUnit =  RomanDigit.convertToInteger(romanDigit);
			
			//total credit
			int credits = Integer.valueOf(value[4]);
			
			//1 rare element unit = credit / intergalactic unit
			RareElement.valueOf(value[2]).setValue((double)credits/intergalacticUnit);
		} 
	}
	
	private int intergalacticeToNumber(String[] data) {
		String romanDigit =  Arrays.stream(data)
								  .map(value -> getValue(value.toUpperCase()))
								  .reduce("", String::concat);
		return RomanDigit.convertToInteger(romanDigit);					  
	}
	
	private String getValue(String id) {
		return UtilityInterface.INTERGALACTICE_To_ROMAN.get(id);
	}
	
	public int calculateCredit(String []data) {
			if (data[data.length - 1].equals("Silver") || data[data.length - 1].equals("Gold")
					 || data[data.length - 1].equals("Iron")) {
				int intergalactic = intergalacticeToNumber(Arrays.copyOf(data, data.length - 1));
				double rareElement = RareElement.valueOf(data[data.length - 1].toUpperCase()).getValue();
				return (int)(intergalactic * rareElement);
			}  
			return intergalacticeToNumber(data);
		}
}

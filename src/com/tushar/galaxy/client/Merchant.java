package com.tushar.galaxy.client;

import java.io.IOException;

import com.tushar.galaxy.service.MerchantService;
import com.tushar.galaxy.utility.UtilityInterface;

public class Merchant {

	public static void main(String[] args) throws IOException {
		UtilityInterface.readIput("input.txt");
		
		MerchantService service = new MerchantService();
		service.updateRareElementValues();
		
		for (String line : UtilityInterface.CONVERSION_QUESTIONS) {
			String []data = null;
			String question = null;
			if (line.contains("how many Credits is")) {
				question = line.replace("how many Credits is ", "").replace("?", "");
				data = question.split("\\s+");
			} else if (line.contains("how much is ")) {
				question = line.replace("how much is ", "").replace("?", "");
				data = question.split("\\s+");
			} else {
				System.out.println("I have no idea what you are talking about");
				continue;
			}
			
			System.out.println(question  + " " + service.calculateCredit(data));
		}
	}
}

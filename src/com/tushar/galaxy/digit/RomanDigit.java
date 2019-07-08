package com.tushar.galaxy.digit;

import java.util.HashMap;
import java.util.Map;

public enum RomanDigit {

	I(1, true), V(5, true), X(10, true), L(50, false), C(100, true), D(500, false), M(1000, false);

	int value;
	boolean canRepeat;

	RomanDigit(int value, boolean canRepeat) {
		this.value = value;
		this.canRepeat = canRepeat;
	}

	public static int convertToInteger(String romanDigit) {
		validateRomanDigit(romanDigit);
		return calculateValue(romanDigit);
	}

	private static boolean isPresent(Map<String, Integer> digits, String digit) {
		return digits.get(digit) != null && digits.get(digit) > 1;
	}
	
	private static void validateRomanDigit(String romanDigit) {
		String[] digits = romanDigit.split("");

		Map<String, Integer> frequncies = new HashMap<>();
		
		//find the frequency of each character
		for (String digit : digits) {
			frequncies.put(digit, frequncies.get(digit) == null ? 1 : frequncies.get(digit) + 1);
		}
		
		// D, L, V can not be repeated
		if (isPresent(frequncies, "D") || isPresent(frequncies, "L") || isPresent(frequncies, "V")) {
			throw new IllegalArgumentException();
		}

		// The symbols "I", "X", "C", and "M" can be repeated three times in succession,
		// but no more. four times if the third and fourth are separated by a smaller
		// value, such as XXXIX
		int frequency = 0;
		RomanDigit previous =  null;
		for (int i = 0; i < digits.length; i++) {
			RomanDigit current = RomanDigit.valueOf(digits[i]);
			RomanDigit next = null;

			if ((i + 1) < digits.length)
				next = RomanDigit.valueOf(digits[i + 1]);
			
			int nextValue = next == null ? -1 : next.ordinal();

			if (current.equals(previous)) {
				// no next value to check whether smaller or greater
				if (frequency > 3 && nextValue == -1) throw new IllegalArgumentException();
				
				// if more than 3 times a character and no smaller
				//character in between
				if (current.ordinal() < nextValue
						&& current.ordinal() < previous.ordinal() 
						&& frequency > 3) {
					throw new IllegalArgumentException();
				}

				frequency++;

				// if frequency is 3 than i want to check whether
				//it is followed by smaller character and than 4
				//is repeat before resetting counter
				if (frequency == 3) {
					continue;
				}
			} else
				frequency = 1;
			previous = current;
		}
	}

	private static int calculateValue(String romanDigit) {
		String[] digits = romanDigit.split("");
		int value = 0;

		for (int i = 0; i < digits.length; i++) {
			RomanDigit currentDigit = RomanDigit.valueOf(digits[i]);
			int currenOrdinal = currentDigit.ordinal();

			int nextOrdinal = -1;
			RomanDigit nextDigit = null;

			if ((i + 1) < digits.length) {
				nextDigit = RomanDigit.valueOf(digits[i + 1]);
				nextOrdinal = nextDigit.ordinal();
			}

			// if i subtracted next digit from current digit
			// i should skip next digit to avoid counting twice
			if (nextOrdinal > currenOrdinal) {
				value += (nextDigit.value - currentDigit.value);
				i++;
			} else {
				value += currentDigit.value;
			}
		}
		return value;
	}

	public int getValue() {
		return value;
	}
}

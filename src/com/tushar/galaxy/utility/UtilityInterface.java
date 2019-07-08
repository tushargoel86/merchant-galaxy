package com.tushar.galaxy.utility;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.tushar.galaxy.digit.IntergalacticDigit;
import com.tushar.galaxy.digit.RomanDigit;

public interface UtilityInterface {

	Set<IntergalacticDigit> INTERGALACTICS = new HashSet<>();
	Map<String, String> INTERGALACTICE_To_ROMAN = new HashMap<>();
	
	List<String> CONVERSION_MATRIX = new ArrayList<>();
	List<String> CONVERSION_QUESTIONS = new ArrayList<>();

	String SPACE_REGEX = "\\s+";
	
	public static void readIput(String path) throws IOException {
		try (Scanner scanner = new Scanner(Paths.get(path))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line == null) break;
				if (line.contains("how many") || line.startsWith("how much")) {
					CONVERSION_QUESTIONS.add(line);
				} else if (line.endsWith("Credits")) {
					CONVERSION_MATRIX.add(line);
				} else {
					String[] data = line.split(SPACE_REGEX);
					IntergalacticDigit interglactic = IntergalacticDigit.valueOf(data[0].toUpperCase());
					interglactic.setValue(RomanDigit.valueOf(data[2]).getValue());
					INTERGALACTICS.add(interglactic);
					
					INTERGALACTICE_To_ROMAN.put(data[0].toUpperCase(), data[2]);
				}
			}

		}
	}
	
}
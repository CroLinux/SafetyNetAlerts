package com.safetynet.alerts.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Utils {

	public static String calculateAge(String dateStringProvided) {
		// Format the String date provided
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate birthDate = LocalDate.parse(dateStringProvided, formatter);

		// Calculate the years
		LocalDate currentDate = LocalDate.now();
		Period age = Period.between(birthDate, currentDate);

		// return in String format the age.getYears();
		return String.valueOf(age.getYears());
	}

}

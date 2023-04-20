package com.safetynet.alerts.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilsTest {

	@Test
	void testCalculateAge() {
		// Test with a birthdate from 50 years ago
		String birthdate = "04/20/1973";
		String expectedAge = "50";
		assertEquals(expectedAge, Utils.calculateAge(birthdate));

		// Test with a birthdate from 15 years ago
		birthdate = "04/20/2008";
		expectedAge = "15";
		assertEquals(expectedAge, Utils.calculateAge(birthdate));

		// Test with the current date
		birthdate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		expectedAge = "0";
		assertEquals(expectedAge, Utils.calculateAge(birthdate));
	}

	@Test
	void testCalculateAgeWithInvalidDate() {
		// Test with an invalid date
		String birthdate = "13/32/2000";
		assertThrows(java.time.format.DateTimeParseException.class, () -> Utils.calculateAge(birthdate));
	}
	
    @Test
    void testConstructor() {
        Utils utils = new Utils();
        assertNotNull(utils);
    }

}

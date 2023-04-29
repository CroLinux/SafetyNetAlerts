package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class URL4ResponseFieldsTest {
	
	@Test
	void testDefaultConstructor() {
		URL4ResponseFields response = new URL4ResponseFields();
		assertNotNull(response);
	}

	@Test
	void testConstructorWithArgumentsConstructor() {
		List<String> medications = new ArrayList<>(Arrays.asList("medication1", "medication2"));
		List<String> allergies = new ArrayList<>(Arrays.asList("allergie1", "allergie2"));
		URL4ResponseFields response = new URL4ResponseFields(1, "Person", "Test", "123-456-7890", "43", medications, allergies);
		assertEquals(1, response.getStation());
		assertEquals("Person", response.getFirstName());
		assertEquals("Test", response.getLastName());
        assertEquals("123-456-7890", response.getPhone());
		assertEquals("43", response.getAge());
        assertEquals(medications, response.getMedications());
        assertEquals(allergies, response.getAllergies());
	}

	@Test
	void testSetters() {
		URL4ResponseFields url4ResponseFields = new URL4ResponseFields();
		url4ResponseFields.setStation(1);
		url4ResponseFields.setFirstName("Person");
		url4ResponseFields.setLastName("Test");
		url4ResponseFields.setPhone("123-456-7890");
		url4ResponseFields.setAge("43");
		List<String> medications = new ArrayList<>(Arrays.asList("medication1", "medication2"));
		url4ResponseFields.setMedications(medications);
		List<String> allergies = new ArrayList<>(Arrays.asList("allergie1", "allergie2"));
		url4ResponseFields.setAllergies(allergies);

		assertEquals(1, url4ResponseFields.getStation());
		assertEquals("Person", url4ResponseFields.getFirstName());
		assertEquals("Test", url4ResponseFields.getLastName());
        assertEquals("123-456-7890", url4ResponseFields.getPhone());
		assertEquals("43", url4ResponseFields.getAge());
		assertEquals(medications, url4ResponseFields.getMedications());
		assertEquals(allergies, url4ResponseFields.getAllergies());
	}

	@Test
	void testToString() {
		URL4ResponseFields url4ResponseFields = new URL4ResponseFields(1, "Person", "Test", "123-456-7890", "43", new ArrayList<>(), new ArrayList<>());
		String expectedToString = "URL4ResponseFields(station=1, firstName=Person, lastName=Test, phone=123-456-7890, age=43, medications=[], allergies=[])";
		assertEquals(expectedToString, url4ResponseFields.toString());
	}

	@Test
	void testHashCode() {
		URL4ResponseFields url4ResponseFields1 = new URL4ResponseFields(1, "Person", "Test", "123-456-7890", "43", new ArrayList<>(), new ArrayList<>());
		URL4ResponseFields url4ResponseFields2 = new URL4ResponseFields(1, "Person", "Test", "123-456-7890", "43", new ArrayList<>(), new ArrayList<>());
		assertEquals(url4ResponseFields1.hashCode(), url4ResponseFields2.hashCode());
	}

	@Test
	void testEquals() {
		URL4ResponseFields url4ResponseFields1 = new URL4ResponseFields(1, "Person", "Test", "123-456-7890", "43", new ArrayList<>(), new ArrayList<>());
		URL4ResponseFields url4ResponseFields2 = new URL4ResponseFields(1, "Person", "Test", "123-456-7890", "43", new ArrayList<>(), new ArrayList<>());
		URL4ResponseFields url4ResponseFields3 = new URL4ResponseFields(1, "Person 3", "Test 3", "123-456-7890", "43", new ArrayList<>(), new ArrayList<>());
		assertTrue(url4ResponseFields1.equals(url4ResponseFields2));
		assertFalse(url4ResponseFields1.equals(url4ResponseFields3));
	}

	@Test
	public void URL4HashCodeTest() {

		URL4ResponseFields URL = new URL4ResponseFields();
		int code = URL.hashCode();

		assertNotEquals(0, code);

	}
}

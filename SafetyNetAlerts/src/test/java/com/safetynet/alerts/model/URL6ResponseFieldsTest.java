package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class URL6ResponseFieldsTest {
	
	@Test
	void testDefaultConstructor() {
		URL6ResponseFields response = new URL6ResponseFields();
		assertNotNull(response);
	}

	@Test
	void testConstructorWithArgumentsConstructor() {
		List<String> medications = new ArrayList<>(Arrays.asList("medication1", "medication2"));
		List<String> allergies = new ArrayList<>(Arrays.asList("allergie1", "allergie2"));
		URL6ResponseFields response = new URL6ResponseFields("Person", "Test", "123 Street", "43", "persontest@mail.com", medications, allergies);
		assertEquals("Person", response.getFirstName());
		assertEquals("Test", response.getLastName());
        assertEquals("123 Street", response.getAddress());
		assertEquals("43", response.getAge());
		assertEquals("persontest@mail.com", response.getEmail());
        assertEquals(medications, response.getMedication());
        assertEquals(allergies, response.getAllergies());
	}

	@Test
	void testSetters() {
		URL6ResponseFields url6ResponseFields = new URL6ResponseFields();
		url6ResponseFields.setFirstName("Person");
		url6ResponseFields.setLastName("Test");
		url6ResponseFields.setAddress("123 Street");
		url6ResponseFields.setAge("43");
		url6ResponseFields.setEmail("persontest@mail.com");
		List<String> medications = new ArrayList<>(Arrays.asList("medication1", "medication2"));
		url6ResponseFields.setMedication(medications);
		List<String> allergies = new ArrayList<>(Arrays.asList("allergie1", "allergie2"));
		url6ResponseFields.setAllergies(allergies);

		assertEquals("Person", url6ResponseFields.getFirstName());
		assertEquals("Test", url6ResponseFields.getLastName());
        assertEquals("123 Street", url6ResponseFields.getAddress());
		assertEquals("43", url6ResponseFields.getAge());
		assertEquals("persontest@mail.com", url6ResponseFields.getEmail());
		assertEquals(medications, url6ResponseFields.getMedication());
		assertEquals(allergies, url6ResponseFields.getAllergies());
	}

	@Test
	void testToString() {
		URL6ResponseFields url6ResponseFields = new URL6ResponseFields("Person", "Test", "123 Street", "43", "persontest@mail.com", new ArrayList<>(), new ArrayList<>());
		String expectedToString = "URL6ResponseFields(firstName=Person, lastName=Test, address=123 Street, age=43, email=persontest@mail.com, medication=[], allergies=[])";
		assertEquals(expectedToString, url6ResponseFields.toString());
	}

	@Test
	void testHashCode() {
		URL6ResponseFields url6ResponseFields1 = new URL6ResponseFields("Person", "Test", "123 Street", "43", "persontest@mail.com", new ArrayList<>(), new ArrayList<>());
		URL6ResponseFields url6ResponseFields2 = new URL6ResponseFields("Person", "Test", "123 Street", "43", "persontest@mail.com", new ArrayList<>(), new ArrayList<>());
		assertEquals(url6ResponseFields1.hashCode(), url6ResponseFields2.hashCode());
	}

	@Test
	void testEquals() {
		URL6ResponseFields url6ResponseFields1 = new URL6ResponseFields("Person", "Test", "123 Street", "43", "persontest@mail.com", new ArrayList<>(), new ArrayList<>());
		URL6ResponseFields url6ResponseFields2 = new URL6ResponseFields("Person", "Test", "123 Street", "43", "persontest@mail.com", new ArrayList<>(), new ArrayList<>());
		URL6ResponseFields url6ResponseFields3 = new URL6ResponseFields("Person", "Test", "123 Street 2", "43", "persontest@mail.com", new ArrayList<>(), new ArrayList<>());
		
		assertTrue(url6ResponseFields1.equals(url6ResponseFields2));
		assertFalse(url6ResponseFields1.equals(url6ResponseFields3));
	}

}

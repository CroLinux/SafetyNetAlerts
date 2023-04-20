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
public class URL2ResponseFieldsTest {

	@Test
	void testDefaultConstructor() {
		URL2ResponseFields response = new URL2ResponseFields();
		assertNotNull(response);
	}

	@Test
	void testConstructorWithArgumentsConstructor() {
		List<String> withResidents = new ArrayList<>(Arrays.asList("Tom", "Jerry"));
		URL2ResponseFields response = new URL2ResponseFields("Person", "Test", "43", withResidents);
		assertEquals("Person", response.getFirstName());
		assertEquals("Test", response.getLastName());
		assertEquals("43", response.getAge());
		assertEquals(withResidents, response.getWithResidents());
	}

	@Test
	void testSetters() {
		URL2ResponseFields url2ResponseFields = new URL2ResponseFields();
		url2ResponseFields.setFirstName("Person");
		url2ResponseFields.setLastName("Test");
		url2ResponseFields.setAge("43");
		List<String> withResidents = new ArrayList<>(Arrays.asList("Tom", "Jerry"));
		url2ResponseFields.setWithResidents(withResidents);

		assertEquals("Person", url2ResponseFields.getFirstName());
		assertEquals("Test", url2ResponseFields.getLastName());
		assertEquals("43", url2ResponseFields.getAge());
		assertEquals(withResidents, url2ResponseFields.getWithResidents());
	}

	@Test
	void testToString() {
		URL2ResponseFields url2ResponseFields = new URL2ResponseFields("Person", "Test", "43", new ArrayList<>());
		String expectedToString = "URL2ResponseFields(firstName=Person, lastName=Test, age=43, withResidents=[])";
		assertEquals(expectedToString, url2ResponseFields.toString());
	}

	@Test
	void testHashCode() {
		URL2ResponseFields url2ResponseFields1 = new URL2ResponseFields("Person", "Test", "43", new ArrayList<>());
		URL2ResponseFields url2ResponseFields2 = new URL2ResponseFields("Person", "Test", "43", new ArrayList<>());
		assertEquals(url2ResponseFields1.hashCode(), url2ResponseFields2.hashCode());
	}

	@Test
	void testEquals() {
		URL2ResponseFields url2ResponseFields1 = new URL2ResponseFields("Person", "Test", "43", new ArrayList<>());
		URL2ResponseFields url2ResponseFields2 = new URL2ResponseFields("Person", "Test", "43", new ArrayList<>());
		URL2ResponseFields url2ResponseFields3 = new URL2ResponseFields("Person 3", "Test 3", "43", new ArrayList<>());
		assertTrue(url2ResponseFields1.equals(url2ResponseFields2));
		assertFalse(url2ResponseFields1.equals(url2ResponseFields3));
	}

}

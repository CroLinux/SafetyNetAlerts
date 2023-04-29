package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class URL5ResponseFieldsTest {

	@Test
	void testDefaultConstructor() {
		URL5ResponseFields response = new URL5ResponseFields();
		assertNotNull(response);
	}

	@Test
	void testGenerateConstructor() {
		String address = "123 Street";
		List<PersonNamePhoneAgeMedicalRecords> residents = new ArrayList<>();
		URL5ResponseFields responseFields = new URL5ResponseFields(address, residents);
		assertEquals("123 Street", responseFields.getAddress());
		assertEquals(residents, responseFields.getResidents());
	}

	@Test
	void testSetters() {
		URL5ResponseFields url5ResponseFields = new URL5ResponseFields();
		List<PersonNamePhoneAgeMedicalRecords> residents = new ArrayList<>();
		url5ResponseFields.setResidents(residents);
		url5ResponseFields.setAddress("123 Street");

		assertEquals("123 Street", url5ResponseFields.getAddress());
		assertEquals(residents, url5ResponseFields.getResidents());
	}

	@Test
	void testSetResidents() {
		URL5ResponseFields responseFields = new URL5ResponseFields();
		PersonNamePhoneAgeMedicalRecords resident = new PersonNamePhoneAgeMedicalRecords();
		responseFields.setResidents(resident);
		assertEquals(1, responseFields.getResidents().size());
		assertTrue(responseFields.getResidents().contains(resident));
	}

	@Test
	void testToString() {
		URL5ResponseFields url5ResponseFields = new URL5ResponseFields("123 Street", new ArrayList<>());
		String expectedToString = "URL5ResponseFields(address=123 Street, residents=[])";
		assertEquals(expectedToString, url5ResponseFields.toString());
	}

	@Test
	void testHashCode() {
		URL5ResponseFields url5ResponseFields1 = new URL5ResponseFields("123 Street", new ArrayList<>());
		URL5ResponseFields url5ResponseFields2 = new URL5ResponseFields("123 Street", new ArrayList<>());
		assertEquals(url5ResponseFields1.hashCode(), url5ResponseFields2.hashCode());
	}

	@Test
	void testEquals() {
		URL5ResponseFields url5ResponseFields1 = new URL5ResponseFields("123 Street", new ArrayList<>());
		URL5ResponseFields url5ResponseFields2 = new URL5ResponseFields("123 Street", new ArrayList<>());
		URL5ResponseFields url5ResponseFields3 = new URL5ResponseFields("123 Street 3", new ArrayList<>());
		assertTrue(url5ResponseFields1.equals(url5ResponseFields2));
		assertFalse(url5ResponseFields1.equals(url5ResponseFields3));
	}

	@Test
	public void URL5HashCodeTest() {

		URL5ResponseFields URL = new URL5ResponseFields();
		int code = URL.hashCode();

		assertNotEquals(0, code);

	}
}

package com.safetynet.alerts.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class PersonNamePhoneAgeMedicalRecordsTest {

	private PersonNamePhoneAgeMedicalRecords personTest, person2;

	@BeforeEach
	public void setUp() {
		List<String> medications = new ArrayList<>(Arrays.asList("medication1", "medication2"));
		List<String> allergies = new ArrayList<>(Arrays.asList("allergie1", "allergie2"));
		personTest = new PersonNamePhoneAgeMedicalRecords("Person", "Test", "123-456-7890", "43", medications,
				allergies);
		person2 = new PersonNamePhoneAgeMedicalRecords("Person", "Test", "123-456-7890", "43", medications, allergies);
	}

	@Test
	public void testDefaultConstructor() {
		PersonNamePhoneAgeMedicalRecords person = new PersonNamePhoneAgeMedicalRecords();
		assertNotNull(person);
	}

	@Test
	public void testGenerateConstructor() {
		List<String> medications2 = new ArrayList<>(Arrays.asList("medication1", "medication2"));
		List<String> allergies2 = new ArrayList<>(Arrays.asList("allergy1", "allergy2"));
		PersonNamePhoneAgeMedicalRecords person = new PersonNamePhoneAgeMedicalRecords("John", "Doe", "123-456-7890",
				"25", medications2, allergies2);

		assertNotNull(person);
		assertEquals("John", person.getFirstName());
		assertEquals("Doe", person.getLastName());
		assertEquals("123-456-7890", person.getPhone());
		assertEquals("25", person.getAge());
		assertEquals(medications2, person.getMedications());
		assertEquals(allergies2, person.getAllergies());
	}

	@Test
	public void testPersonCreation() {
		assertNotNull(personTest);
	}

	@Test
	public void testPersonFields() {
		assertEquals("Person", personTest.getFirstName());
		assertEquals("Test", personTest.getLastName());
		assertEquals("123-456-7890", personTest.getPhone());
		assertEquals("43", personTest.getAge());
		assertEquals(2, personTest.getMedications().size());
		assertEquals("medication1", personTest.getMedications().get(0));
		assertEquals(2, personTest.getAllergies().size());
		assertEquals("allergie2", personTest.getAllergies().get(1));
	}

	@Test
	public void testPersonToString() {
		String expected = "PersonNamePhoneAgeMedicalRecords(firstName=Person, lastName=Test, phone=123-456-7890, age=43, medications=[medication1, medication2], allergies=[allergie1, allergie2])";
		String result = personTest.toString();
		assertEquals(expected, result);
	}

	@Test
	public void testSetters() {
		person2.setFirstName("Person 2");
		person2.setLastName("Test 2");
		person2.setPhone("222-222-2222");
		person2.setAge("45");
		List<String> newMedications = new ArrayList<>(Arrays.asList("medication3", "medication4"));
		person2.setMedications(newMedications);
		List<String> newAllergies = new ArrayList<>(Arrays.asList("allergie3", "allergie4"));
		person2.setAllergies(newAllergies);

		assertEquals("Person 2", person2.getFirstName());
        assertEquals("Test 2", person2.getLastName());
		assertEquals("222-222-2222", person2.getPhone());
		assertEquals("45", person2.getAge());
		assertEquals(newMedications, person2.getMedications());
		assertEquals(newAllergies, person2.getAllergies());
	}

	@Test
	public void testPersonHashCode() {
		int hash1 = personTest.hashCode();
		int hash2 = person2.hashCode();
		assertEquals(hash1, hash2);
	}

	@Test
	public void testPersonEquals() {
		assertTrue(personTest.equals(person2));
		// assertTrue(person2.equals(person1));

		person2.setPhone("999-999-9999");
		assertFalse(personTest.equals(person2));
		// assertFalse(person2.equals(person1));
	}
	
	@Test
	public void MoreHashCodeTest() {

		PersonNamePhoneAgeMedicalRecords PersonHash = new PersonNamePhoneAgeMedicalRecords();
		int code = PersonHash.hashCode();

		assertNotEquals(0, code);

	}

}

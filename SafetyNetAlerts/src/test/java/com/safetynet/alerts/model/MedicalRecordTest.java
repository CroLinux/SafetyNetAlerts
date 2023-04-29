package com.safetynet.alerts.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.ArrayList;

@SpringBootTest
public class MedicalRecordTest {

	private MedicalRecord medicalRecord;
	private MedicalRecord medicalRecord1;

	@BeforeEach
	public void setUp() throws Exception {
		List<String> medications = new ArrayList<String>();
		medications.add("medication1");
		medications.add("medication2");
		List<String> allergies = new ArrayList<String>();
		allergies.add("allergie1");
		allergies.add("allergie2");
		medicalRecord = new MedicalRecord("Person", "Test", "01/01/1980", medications, allergies);
		medicalRecord1 = new MedicalRecord("Person", "Test", "01/01/1980", medications, allergies);
	}

	@Test
	public void testDefaultConstructor() {
		MedicalRecord mr = new MedicalRecord();
		assertNotNull(mr);
	}

	@Test
	public void testGenerateConstructor() {
		List<String> medications = new ArrayList<String>();
		medications.add("medication1");
		medications.add("medication2");
		List<String> allergies = new ArrayList<String>();
		allergies.add("allergie1");
		allergies.add("allergie2");
		MedicalRecord mr = new MedicalRecord("Person", "Test", "01/01/1980", medications, allergies);
		assertNotNull(mr);
		assertEquals("Person", mr.getFirstName());
		assertEquals("Test", mr.getLastName());
		assertEquals("01/01/1980", mr.getBirthdate());
		assertEquals(medications, mr.getMedications());
		assertEquals(allergies, mr.getAllergies());
	}

	@Test
	public void testGetFirstName() {
		assertEquals("Person", medicalRecord.getFirstName());
		assertEquals("Test", medicalRecord.getLastName());
		assertEquals("01/01/1980", medicalRecord.getBirthdate());

		List<String> expected = new ArrayList<String>();
		expected.add("medication1");
		expected.add("medication2");
		assertEquals(expected, medicalRecord.getMedications());
		List<String> expected2 = new ArrayList<String>();
		expected2.add("allergie1");
		expected2.add("allergie2");
		assertEquals(expected2, medicalRecord.getAllergies());
	}

	@Test
	public void testSetters() {
		medicalRecord.setFirstName("Jane");
		assertEquals("Jane", medicalRecord.getFirstName());
		medicalRecord.setLastName("Smith");
		assertEquals("Smith", medicalRecord.getLastName());
		medicalRecord.setBirthdate("02/02/1990");
		assertEquals("02/02/1990", medicalRecord.getBirthdate());

		List<String> newMedications = new ArrayList<String>();
		newMedications.add("medication3");
		medicalRecord.setMedications(newMedications);
		assertEquals(newMedications, medicalRecord.getMedications());
		List<String> newAllergies = new ArrayList<String>();
		newAllergies.add("allergy3");
		medicalRecord.setAllergies(newAllergies);
		assertEquals(newAllergies, medicalRecord.getAllergies());
	}

	@Test
	public void testToString() {
		String expected = "MedicalRecord(firstName=Person, lastName=Test, birthdate=01/01/1980, medications=[medication1, medication2], allergies=[allergie1, allergie2])";
		assertEquals(expected, medicalRecord1.toString());
	}

	@Test
	public void testHashCode() {
		assertEquals(medicalRecord.hashCode(), medicalRecord1.hashCode());
	}

	@Test
	public void testEquals() {
		assertTrue(medicalRecord.equals(medicalRecord1));
	}
	
    @Test
    public void MedicalRecordHashCodeTest() {

        MedicalRecord medicalRecordTest = new MedicalRecord();;
        int code = medicalRecordTest.hashCode();

        assertNotEquals(0, code);

    }

}

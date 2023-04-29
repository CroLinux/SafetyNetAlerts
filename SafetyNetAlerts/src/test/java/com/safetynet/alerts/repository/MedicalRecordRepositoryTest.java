package com.safetynet.alerts.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.model.MedicalRecord;

import com.safetynet.alerts.utils.CopyJsonFile;

@SpringBootTest
class MedicalRecordRepositoryTest {

	private MedicalRecordRepository medicalRecordRepository;

	CopyJsonFile copyJsonFile = new CopyJsonFile();

	@BeforeEach
	void setUp() throws IOException {
		medicalRecordRepository = new MedicalRecordRepository();
		CopyJsonFile.copyJsonFile();

	}

	@Test
	void testGetMedicalRecords() throws IOException {
		// Given

		// When
		List<MedicalRecord> result = medicalRecordRepository.getMedicalRecords();
		// Then
		assertNotEquals(1, result.size());
		MedicalRecord medicalRecord = result.get(0);
		assertEquals("John", medicalRecord.getFirstName());
		assertEquals("Boyd", medicalRecord.getLastName());
		assertEquals("03/06/1984", medicalRecord.getBirthdate());
		assertEquals(2, medicalRecord.getMedications().size());
		assertEquals(1, medicalRecord.getAllergies().size());
	}

	@Test
	void testGetMedicalRecordInDataSource() throws IOException {
		// Given
		String firstName = "John";
		String lastName = "Boyd";
		// When
		List<MedicalRecord> result = medicalRecordRepository.getMedicalRecordInDataSource(firstName, lastName);
		// Then
		assertEquals(1, result.size());
		MedicalRecord medicalRecord = result.get(0);
		assertEquals("John", medicalRecord.getFirstName());
		assertEquals("Boyd", medicalRecord.getLastName());
		assertEquals("03/06/1984", medicalRecord.getBirthdate());
		assertEquals(2, medicalRecord.getMedications().size());
		assertEquals(1, medicalRecord.getAllergies().size());
	}

	@Test
	public void testAddMedicalRecordInDataSource() throws IOException {
		// Given
		MedicalRecord medicalRecordToAdd = new MedicalRecord("AddPerson", "Test", "01/01/1990", new ArrayList<>(),
				new ArrayList<>());
		MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
		// When
		// We add the new medical record
		medicalRecordRepository.addMedicalRecordInDataSource(medicalRecordToAdd);
		// We get the new content of the file
		List<MedicalRecord> result = medicalRecordRepository.getMedicalRecords();
		// Then
		assertNotNull(result);
		assertTrue(result.contains(medicalRecordToAdd));
	}

	@Test
	public void testUpdateMedicalRecordInDataSource() throws IOException {
		// Given
		MedicalRecord medicalRecordToUpdate = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("medication1", "medication2"), Arrays.asList("allergy1", "allergy2"));
		MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
		MedicalRecord updatedMedicalRecord = new MedicalRecord("John", "Boyd", "01/01/1990",
				Arrays.asList("medication3"), Arrays.asList("allergy1"));
		// When
		// Update the medical Record
		medicalRecordRepository.updateMedicalRecordInDataSource(updatedMedicalRecord);
		// Get the new list
		List<MedicalRecord> result = medicalRecordRepository.getMedicalRecords();
		// Then
		System.out.println(result);
		assertNotNull(result);
		assertTrue(result.contains(updatedMedicalRecord));
		assertFalse(result.contains(medicalRecordToUpdate));
	}

	@Test
	public void testDeleteMedicalRecordInDataSource() throws IOException {
		// Given
		MedicalRecord medicalRecordToDelete = new MedicalRecord("John", "Boyd", "03/06/1984",
				Arrays.asList("medication1", "medication2"), Arrays.asList("allergy1", "allergy2"));
		MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
		// When
		// Update the medical Record
		medicalRecordRepository.deleteMedicalRecordInDataSource(medicalRecordToDelete);
		// Get the new list
		List<MedicalRecord> result = medicalRecordRepository.getMedicalRecords();
		// Then
		System.out.println(result);
		assertNotNull(result);
		assertFalse(result.contains(medicalRecordToDelete));
	}

}

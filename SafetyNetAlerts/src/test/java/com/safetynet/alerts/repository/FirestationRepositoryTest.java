package com.safetynet.alerts.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.utils.CopyJsonFile;

@SpringBootTest
public class FirestationRepositoryTest {

	private FirestationRepository firestationRepository;

	CopyJsonFile copyJsonFile = new CopyJsonFile();

	@BeforeEach
	void setUp() throws IOException {
		firestationRepository = new FirestationRepository();
		CopyJsonFile.copyJsonFile();

	}

	@Test
	void testGetFirestations() throws IOException {
		// Given

		// When
		List<Firestation> result = firestationRepository.getFirestations();
		Firestation firestation = result.get(0);
		// Then
		assertNotEquals(1, result.size());
		assertEquals("1509 Culver St", firestation.getAddress());
		assertEquals(3, firestation.getStation());

	}

	@Test
	void testGetFirestationsByID() throws IOException {
		// Given
		int stationTest = 3;
		// When
		List<Firestation> result = firestationRepository.getFirestationsByID(stationTest);
		Firestation firestation = result.get(0);
		// Then
		assertNotEquals(1, result.size());
		assertEquals("1509 Culver St", firestation.getAddress());
		assertEquals(3, firestation.getStation());

	}

	@Test
	void testGetFirestationsAddressByID() throws IOException {
		// Given
		int stationTest = 3;
		// When
		HashSet<String> result = firestationRepository.getFirestationsAddressByID(stationTest);
		// Then
		assertNotEquals(1, result.size());

	}

	@Test
	void testGetFirestationInDataSource() throws IOException {
		// Given
		int stationTest = 3;
		// When
		List<Firestation> result = firestationRepository.getFirestationInDataSource(stationTest);
		Firestation firestation = result.get(0);
		// Then
		assertNotEquals(1, result.size());
		assertEquals("1509 Culver St", firestation.getAddress());
		assertEquals(3, firestation.getStation());

	}

	@Test
	public void testAddFirestationInDataSource() throws IOException {
		// Given
		Firestation firestationToAdd = new Firestation("123 Test Street", 9);
		FirestationRepository firestationRepository = new FirestationRepository();
		// When
		// We add the new Firestation
		firestationRepository.addFirestationInDataSource(firestationToAdd);
		// We get the new list
		List<Firestation> result = firestationRepository.getFirestations();
		// Then
		assertNotNull(result);
		assertTrue(result.contains(firestationToAdd));
	}

	@Test
	public void testUpdateFirestationInDataSource() throws IOException {
		// Given
		Firestation firestationToUpdate = new Firestation("1509 Culver St", 3);
		Firestation updatedFirestation = new Firestation("1509 Culver St", 8);
		FirestationRepository firestationRepository = new FirestationRepository();
		// When
		// Update the Firestation
		firestationRepository.updateFirestationInDataSource(updatedFirestation);
		// Get the new list
		List<Firestation> result = firestationRepository.getFirestations();
		// Then
		assertNotNull(result);
		assertTrue(result.contains(updatedFirestation));
		assertFalse(result.contains(firestationToUpdate));
	}

	@Test
	public void testDeleteFirestationInDataSource() throws IOException {
		// Given
		Firestation firestationToDelete = new Firestation("1509 Culver St", 0);
		FirestationRepository firestationRepository = new FirestationRepository();
		// When
		// Delete the Firestation
		firestationRepository.deleteFirestationInDataSource(firestationToDelete);
		// Get the new list
		List<Firestation> result = firestationRepository.getFirestations();
		// Then
		assertNotNull(result);
		assertFalse(result.contains(firestationToDelete));
	}

}

package com.safetynet.alerts.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.utils.CopyJsonFile;

@SpringBootTest
public class CRUDOnJSONFileTest {

	private CRUDOnJSONFile crudOnJSONFile = new CRUDOnJSONFile();

	CopyJsonFile copyJsonFile = new CopyJsonFile();

	@BeforeEach
	void setUp() throws IOException {

		CopyJsonFile.copyJsonFile();
	}

	@Test
	public void testIsPersonAlreadyExists() throws IOException {

		// Given
		Person personToVerify = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458",
				"gramps@email.com");

		// When and Then
		assertFalse(crudOnJSONFile.isPersonAlreadyExists(personToVerify));

	}
}

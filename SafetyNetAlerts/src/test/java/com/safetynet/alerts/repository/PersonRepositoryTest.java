package com.safetynet.alerts.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonFullData;
import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL2ResponseFields;
import com.safetynet.alerts.model.URL4ResponseFields;
import com.safetynet.alerts.model.URL5ResponseFields;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.utils.CopyJsonFile;

@SpringBootTest
public class PersonRepositoryTest {

	private PersonRepository personRepository;

	CopyJsonFile copyJsonFile = new CopyJsonFile();

	@BeforeEach
	void setUp() throws IOException {
		personRepository = new PersonRepository();
		CopyJsonFile.copyJsonFile();
	}

	@Test
	void testGetPersons() throws IOException {
		// Given

		// When
		List<Person> result = personRepository.getPersons();
		Person person = result.get(0);
		// Then
		assertNotEquals(1, result.size());
		assertEquals("John", person.getFirstName());
	}

	@Test
	void testGetPersonInDataSource() throws IOException {
		// Given
		String firstName = "John";
		String lastName = "Boyd";
		// When
		List<Person> result = personRepository.getPersonInDataSource(firstName, lastName);
		Person person = result.get(0);
		// Then
		assertEquals(1, result.size());
		assertEquals("John", person.getFirstName());
		assertEquals("Boyd", person.getLastName());
		assertEquals("1509 Culver St", person.getAddress());
		assertEquals("Culver", person.getCity());
		assertEquals("97451", person.getZip());
		assertEquals("841-874-6512", person.getPhone());
		assertEquals("jaboyd@email.com", person.getEmail());
	}

	@Test
	void testGetPersonsFullData() throws IOException {
		// Given

		// When
		List<PersonFullData> result = personRepository.getPersonsFullData();
		PersonFullData personFullData = result.get(0);
		// Then
		assertNotEquals(0, result.size());
		assertEquals("John", personFullData.getFirstName());
		assertEquals("Boyd", personFullData.getLastName());
		assertEquals("1509 Culver St", personFullData.getAddress());
		assertEquals("Culver", personFullData.getCity());
		assertEquals("97451", personFullData.getZip());
		assertEquals("841-874-6512", personFullData.getPhone());
		assertEquals("jaboyd@email.com", personFullData.getEmail());
		assertEquals("03/06/1984", personFullData.getBirthdate());
		assertEquals(2, personFullData.getMedications().size());
		assertEquals(1, personFullData.getAllergies().size());
		assertEquals(3, personFullData.getStation());
	}

	@Test
	void testGetFullAdults() throws IOException {
		// Given

		// When
		List<PersonFullData> result = personRepository.getFullAdults();
		// Then
		assertNotEquals(0, result.size());
		for (PersonFullData adultsFullData : result) {
			assertTrue(Integer.parseInt(adultsFullData.getAge()) > 18);
		}
	}

	@Test
	void testGetFullChild() throws IOException {
		// Given

		// When
		List<PersonFullData> result = personRepository.getFullChilds();
		// Then
		assertNotEquals(0, result.size());
		for (PersonFullData childsFullData : result) {
			assertTrue(Integer.parseInt(childsFullData.getAge()) <= 18);
		}
	}

	@Test
	void testGetPersonsByAddress() throws IOException {
		// Given
		String address = "1509 Culver St";
		// Then
		List<Person> result = personRepository.getPersonsByAddress(address);
		Person person = result.get(0);
		// Then
		assertNotEquals(1, result.size());
		assertEquals("John", person.getFirstName());
		assertEquals("Boyd", person.getLastName());
		assertEquals("1509 Culver St", person.getAddress());
		assertEquals("Culver", person.getCity());
		assertEquals("97451", person.getZip());
		assertEquals("841-874-6512", person.getPhone());
		assertEquals("jaboyd@email.com", person.getEmail());

	}

	@Test
	void testGetPeopleAndCountByStationNumber() throws IOException {
		// Given
		int stationNumber = 3;
		// When
		List<URL1ResponseFields> result = personRepository.getPeopleAndCountByStationNumber(stationNumber);
		// Then
		assertNotEquals(0, result.size());
		for (URL1ResponseFields personList : result) {
			assertNotNull(personList.getFirstName());
			assertNotNull(personList.getLastName());
			assertNotNull(personList.getAddress());
			assertNotNull(personList.getPhone());
			assertTrue(personList.getAdults() >= 0);
			assertTrue(personList.getKids() >= 0);
		}
	}

	@Test
	void testGetChildListByAddress() throws IOException {
		// Given
		String address = "1509 Culver St";
		// When
		List<URL2ResponseFields> result = personRepository.getChildListByAddress(address);
		// Then
		assertEquals(2, result.size());
		URL2ResponseFields child = result.get(0);
		assertEquals("Tenley", child.getFirstName());
		assertEquals("Boyd", child.getLastName());
		assertEquals("11", child.getAge()); // This value depends on when you're doing the test
		assertEquals(4, child.getWithResidents().size());
		assertEquals("John Boyd", child.getWithResidents().get(0));
	}

	@Test
	void testGetPhoneByFirestation() throws IOException {
		// Given
		int firestation = 1;
		// When
		List<String> result = personRepository.getPhoneByFirestation(firestation);
		// Then
		assertNotNull(result);
		assertNotEquals(0, result.size());
		assertTrue(result.contains("841-874-6512"));
		assertTrue(result.contains("841-874-8547"));
	}

	@Test
	void testGetFireListByAddress() throws IOException {
		// Given
		String address = "1509 Culver St";
		// When
		List<URL4ResponseFields> result = personRepository.getFireListByAddress(address);
		// Then
		assertNotNull(result);
		assertNotEquals(0, result.size());
		URL4ResponseFields personList = result.get(0);
		assertEquals(3, personList.getStation());
		assertEquals("John", personList.getFirstName());
		assertEquals("Boyd", personList.getLastName());
		assertEquals("841-874-6512", personList.getPhone());
		assertEquals("39", personList.getAge()); // This value depends on when you're doing the test
		assertEquals(2, personList.getMedications().size());
		assertEquals(1, personList.getAllergies().size());
	}

	@Test
	void testGetFloodStationsByFirestation() throws IOException {
		// Given
		List<Integer> firestationList = Arrays.asList(0, 4);
		// When
		List<URL5ResponseFields> result = personRepository.getFloodStationsByFirestation(firestationList);
		// Then
		assertNotNull(result);
		assertNotEquals(0, result.size());

		URL5ResponseFields personList = result.get(0);
		assertEquals("489 Manchester St", personList.getAddress());
		assertEquals(1, personList.getResidents().size());
		assertTrue(personList.getResidents().stream()
				.anyMatch(r -> r.getFirstName().equals("Lily") && r.getLastName().equals("Cooper")));

	}

	@Test
	void testGetPersonAndMedicalRecordsByFirstNameAndLastName() throws IOException {
		// Given
		String firstName = "John";
		String lastName = "Boyd";
		// When
		List<URL6ResponseFields> result = personRepository.getPersonAndMedicalRecordsByFirstNameAndLastName(firstName,
				lastName);
		// Then
		assertNotNull(result);
		assertEquals(1, result.size());
		URL6ResponseFields personList = result.get(0);
		assertEquals("John", personList.getFirstName());
		assertEquals("Boyd", personList.getLastName());
		assertEquals("1509 Culver St", personList.getAddress());
		assertEquals("39", personList.getAge()); // This value depends on when you're doing the test
		assertEquals("jaboyd@email.com", personList.getEmail());
		assertEquals(2, personList.getMedication().size());
		assertEquals(1, personList.getAllergies().size());
	}

	@Test
	void testGetEmailByCity() {
		// Given
		String city = "Culver";
		// When
		List<String> result = personRepository.getEmailByCity(city);
		// Then
		assertNotNull(result);
		assertNotEquals(0, result.size());
		assertTrue(result.contains("jaboyd@email.com"));
		assertTrue(result.contains("drk@email.com"));
	}

	@Test
	public void testAddPersonInDataSource() throws IOException {
		// Given
		Person personToAdd = new Person("Person", "Test", "123 POCR Street", "Paris", "75000", "123-456-7890",
				"persontest@mail.com");
		PersonRepository personRepository = new PersonRepository();
		// When
		// We add the new Person
		personRepository.addPersonInDataSource(personToAdd);
		List<Person> result = personRepository.getPersons();
		// Then
		assertNotNull(result);
		assertTrue(result.contains(personToAdd));
	}
	
	@Test
	public void testAddPersonInDataSourceAlreadyThere() throws IOException {
		// Given
		Person personToAdd = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458",
				"gramps@email.com");
		PersonRepository personRepository = new PersonRepository();
		// When
		// We add the new Person
		personRepository.addPersonInDataSource(personToAdd);
		List<Person> result = personRepository.getPersons();
		// Then
		assertNotNull(result);
		assertTrue(result.contains(personToAdd));
	}

	@Test
	public void testUpdatePersonInDataSource() throws IOException {
		// Given
		Person personToUpdate = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", "97451", "841-874-7458",
				"gramps@email.com");
		Person updatedPerson = new Person("Eric", "Cadigan", "123 OPCR Street", "Paris", "75000", "841-874-7458",
				"gramps@email.com");
		PersonRepository personRepository = new PersonRepository();
		// When
		// Update the Person
		personRepository.updatePersonInDataSource(updatedPerson);
		// Get the new list
		List<Person> result = personRepository.getPersons();
		// Then
		assertNotNull(result);
		assertTrue(result.contains(updatedPerson));
		assertFalse(result.contains(personToUpdate));
	}
	
	@Test
	public void testUpdatePersonInDataSourceNotPresent() throws IOException {
		// Given
		//Person personToUpdate = new Person("Nobody", "Nobody", "Address", "Town", "Zip", "Phone",
		//		"email.com");
		Person updatedPerson = new Person("Nobody", "Nobody", "Address", "Town", "Zip", "Phone",
				"email.com");
		PersonRepository personRepository = new PersonRepository();
		// When
		// Update the Person
		personRepository.updatePersonInDataSource(updatedPerson);
		// Get the new list
		List<Person> result = personRepository.getPersons();
		// Then
		assertNotNull(result);
	}

	@Test
	public void testDeletePersonInDataSource() throws IOException {
		// Given
		Person personToDelete = new Person("Eric", "Cadigan", "", "", "", "", "");
		PersonRepository personRepository = new PersonRepository();
		// When
		// Delete the Firestation
		personRepository.deletePersonInDataSource(personToDelete);
		// Get the new list
		List<Person> result = personRepository.getPersons();
		// Then
		assertNotNull(result);
		assertFalse(result.contains(personToDelete));
	}

	@Test
	public void testDeletePersonInDataSourceNotPresent() throws IOException {
		// Given
		Person personToDelete = new Person("Nobody", "Nobody", "", "", "", "", "");
		PersonRepository personRepository = new PersonRepository();
		// When
		// Delete the Firestation
		personRepository.deletePersonInDataSource(personToDelete);
		// Get the new list
		List<Person> result = personRepository.getPersons();
		// Then
		assertNotNull(result);
		assertFalse(result.contains(personToDelete));
	}
}

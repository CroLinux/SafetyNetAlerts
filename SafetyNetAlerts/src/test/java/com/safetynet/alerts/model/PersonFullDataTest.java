package com.safetynet.alerts.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.ArrayList;

@SpringBootTest
public class PersonFullDataTest {

	private PersonFullData person1;
	private PersonFullData person2;

	@BeforeEach
	public void setUp() {
		List<String> medications1 = new ArrayList<>();
		medications1.add("medication1");
		List<String> allergies1 = new ArrayList<>();
		allergies1.add("allergie1");

		List<String> medications2 = new ArrayList<>();
		medications2.add("medication2");
		List<String> allergies2 = new ArrayList<>();
		allergies2.add("allergie2");

		person1 = new PersonFullData();
		person1.setFirstName("Person");
		person1.setLastName("Test");
		person1.setAddress("123 Street");
		person1.setCity("Paris");
		person1.setZip("75000");
		person1.setPhone("123-456-7890");
		person1.setEmail("persontest@mail.com");
		person1.setBirthdate("01/01/1980");
		person1.setAge("43");
		person1.setMedications(medications1);
		person1.setAllergies(allergies1);
		person1.setStation(1);

		person2 = new PersonFullData();
		person2.setFirstName("Person 2");
		person2.setLastName("Test 2");
		person2.setAddress("123 Street 2");
		person2.setCity("Paris 2");
		person2.setZip("75002");
		person2.setPhone("123-456-7890");
		person2.setEmail("persontest2@mail.com");
		person2.setBirthdate("01/01/1980");
		person2.setAge("43");
		person2.setMedications(medications2);
		person2.setAllergies(allergies2);
		person2.setStation(2);
	}

	@Test
	public void testDefaultConstructor() {
		PersonFullData person = new PersonFullData();
		assertNotNull(person);
	}

	@Test
	public void testConstructorWithParameters() {
		String firstName = "Person";
		String lastName = "Test";
		String address = "123 Street";
		String city = "Paris";
		String zip = "75000";
		String phone = "123-456-7890";
		String email = "persontest@mail.com";
		String birthdate = "01/01/1980";
		String age = "43";
		List<String> medications = new ArrayList<>();
		medications.add("medication1");
		List<String> allergies = new ArrayList<>();
		allergies.add("allergie1");
		int station = 1;

		PersonFullData person = new PersonFullData(firstName, lastName, address, city, zip, phone, email, birthdate,
				age, medications, allergies, station);

		assertEquals(firstName, person.getFirstName());
		assertEquals(lastName, person.getLastName());
		assertEquals(address, person.getAddress());
		assertEquals(city, person.getCity());
		assertEquals(zip, person.getZip());
		assertEquals(phone, person.getPhone());
		assertEquals(email, person.getEmail());
		assertEquals(birthdate, person.getBirthdate());
		assertEquals(age, person.getAge());
		assertEquals(medications, person.getMedications());
		assertEquals(allergies, person.getAllergies());
		assertEquals(station, person.getStation());
	}

	@Test
	public void testToString() {
		assertEquals(
				"PersonFullData(firstName=Person, lastName=Test, address=123 Street, city=Paris, zip=75000, phone=123-456-7890, email=persontest@mail.com, birthdate=01/01/1980, age=43, medications=[medication1], allergies=[allergie1], station=1)",
				person1.toString());
	}

	@Test
	public void testHashCode() {
		assertNotEquals(person1.hashCode(), person2.hashCode());
	}

	@Test
	public void testEquals() {
		assertFalse(person1.equals(person2));
	}

}

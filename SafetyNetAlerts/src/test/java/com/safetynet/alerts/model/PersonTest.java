package com.safetynet.alerts.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PersonTest {
	
    @Test
    public void testDefaultConstructor() {
        Person person = new Person();
        assertNotNull(person);
        assertNull(person.getFirstName());
        assertNull(person.getLastName());
        assertNull(person.getAddress());
        assertNull(person.getCity());
        assertNull(person.getZip());
        assertNull(person.getPhone());
        assertNull(person.getEmail());
    }

    @Test
    public void testConstructorWithArguments() {
        String firstName = "Person";
        String lastName = "Test";
        String address = "123 Street";
        String city = "Paris";
        String zip = "75000";
        String phone = "123-456-7890";
        String email = "persontest@mail.com";

        Person person = new Person(firstName, lastName, address, city, zip, phone, email);

        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(address, person.getAddress());
        assertEquals(city, person.getCity());
        assertEquals(zip, person.getZip());
        assertEquals(phone, person.getPhone());
        assertEquals(email, person.getEmail());
    }

	@Test
	public void testPersonBuilder() {
		Person person = new Person.PersonBuilder().firstName("Person").lastName("Test").address("123 Street")
				.city("Paris").zip("75000").phone("123-456-7890").email("persontest@mail.com").build();

		assertEquals("Person", person.getFirstName());
		assertEquals("Test", person.getLastName());
		assertEquals("123 Street", person.getAddress());
		assertEquals("Paris", person.getCity());
		assertEquals("75000", person.getZip());
		assertEquals("123-456-7890", person.getPhone());
		assertEquals("persontest@mail.com", person.getEmail());
	}

	@Test
	public void personSetAndGetTest() {

		Person personTest = new Person();

		personTest.setFirstName("Person2");
		personTest.setLastName("Test2");
		personTest.setAddress("123 Street 2");
		personTest.setZip("75002");
		personTest.setCity("Paris 2");
		personTest.setPhone("222-222-2222");
		personTest.setEmail("persontest2@mail.com");

		assertEquals("Person2", personTest.getFirstName());
		assertEquals("Test2", personTest.getLastName());
		assertEquals("123 Street 2", personTest.getAddress());
		assertEquals("75002", personTest.getZip());
		assertEquals("Paris 2", personTest.getCity());
		assertEquals("222-222-2222", personTest.getPhone());
		assertEquals("persontest2@mail.com", personTest.getEmail());
	}

	@Test
	public void testEquals() {
		Person person1 = new Person.PersonBuilder().firstName("Person").lastName("Test").address("123 Street")
				.city("Paris").zip("75000").phone("123-456-7890").email("persontest@mail.com").build();

		Person person2 = new Person.PersonBuilder().firstName("Person").lastName("Test").address("123 Street")
				.city("Paris").zip("75000").phone("123-456-7890").email("persontest@mail.com").build();

		Person person3 = new Person.PersonBuilder().firstName("Test").lastName("Person").address("456 No Street")
				.city("Lutece").zip("75099").phone("333-333-3333").email("noperson@mail.com").build();

		assertTrue(person1.equals(person2));
		assertFalse(person1.equals(person3));
	}

	@Test
	void testToString() {
		Person person = new Person.PersonBuilder().firstName("Person").lastName("Test").address("123 Street")
				.city("Paris").zip("75000").phone("123-456-7890").email("persontest@mail.com").build();

		String expected = "Person(firstName=Person, lastName=Test, address=123 Street, city=Paris, zip=75000, phone=123-456-7890, email=persontest@mail.com)";
		String actual = person.toString();

		assertEquals(expected, actual);
	}

	@Test
	void testHashCode() {
		Person person1 = new Person.PersonBuilder().firstName("Person").lastName("Test").address("123 Street")
				.city("Paris").zip("75000").phone("123-456-7890").email("persontest@mail.com").build();

		Person person2 = new Person.PersonBuilder().firstName("Person").lastName("Test").address("123 Street")
				.city("Paris").zip("75000").phone("123-456-7890").email("persontest@mail.com").build();

		Person person3 = new Person.PersonBuilder().firstName("Test").lastName("Person").address("456 No Street")
				.city("Lutece").zip("75099").phone("333-333-3333").email("noperson@mail.com").build();

		int hashCode1 = person1.hashCode();
		int hashCode2 = person2.hashCode();
		int hashCode3 = person3.hashCode();

		assertEquals(hashCode1, hashCode2);
		assertNotEquals(hashCode1, hashCode3);
	}
	
    @Test
    public void PersonHashCodeTest() {

        Person personTest = new Person();
        int code = personTest.hashCode();

        assertNotEquals(0, code);

    }

}

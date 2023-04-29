package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class URL1ResponseFieldsTest {

    @Test
    void testDefaultConstructor() {
        URL1ResponseFields response = new URL1ResponseFields();
        assertNotNull(response);
    }
    
    @Test
    void testConstructorWithArgumentsConstructor() {
        URL1ResponseFields response = new URL1ResponseFields("Person", "Test", "123 Street", "123-456-7890", 1, 1);
        assertEquals("Person", response.getFirstName());
        assertEquals("Test", response.getLastName());
        assertEquals("123 Street", response.getAddress());
        assertEquals("123-456-7890", response.getPhone());
        assertEquals(1, response.getAdults());
        assertEquals(1, response.getKids());
    }
    
    @Test
    void testSetters() {
        URL1ResponseFields response = new URL1ResponseFields();
        response.setFirstName("Person 2");
        response.setLastName("Test");
        response.setAddress("123 Street 2");
        response.setPhone("222-222-2222");
        response.setAdults(2);
        response.setKids(3);
        assertEquals("Person 2", response.getFirstName());
        assertEquals("Test", response.getLastName());
        assertEquals("123 Street 2", response.getAddress());
        assertEquals("222-222-2222", response.getPhone());
        assertEquals(2, response.getAdults());
        assertEquals(3, response.getKids());
    }
    
    @Test
    void testToString() {
        URL1ResponseFields response = new URL1ResponseFields("Person", "Test", "123 Street", "123-456-7890", 2, 3);
        String expectedString = "URL1ResponseFields(firstName=Person, lastName=Test, address=123 Street, phone=123-456-7890, adults=2, kids=3)";
        assertEquals(expectedString, response.toString());
    }
    
    @Test
    void testHashCode() {
        URL1ResponseFields response1 = new URL1ResponseFields("Person", "Test", "123 Street", "123-456-7890", 2, 3);
        URL1ResponseFields response2 = new URL1ResponseFields("Person", "Test", "123 Street", "123-456-7890", 2, 3);
        assertEquals(response1.hashCode(), response2.hashCode());
    }
    
    @Test
    void testEquals() {
        URL1ResponseFields response1 = new URL1ResponseFields("Person", "Test", "123 Street", "123-456-7890", 2, 3);
        URL1ResponseFields response2 = new URL1ResponseFields("Person", "Test", "123 Street", "123-456-7890", 2, 3);
        URL1ResponseFields response3 = new URL1ResponseFields("Person", "Test 2", "123 Street", "123-456-7890", 2, 3);
        assertTrue(response1.equals(response2));
        assertFalse(response1.equals(response3));
    }
    
	@Test
	public void URL1HashCodeTest() {

		URL1ResponseFields URL = new URL1ResponseFields();
		int code = URL.hashCode();

		assertNotEquals(0, code);

	}

}


package com.safetynet.alerts.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class FirestationTest {

	@Test
	public void testConstructor() {
		String address = "123 Street";
		int station = 1;
		Firestation firestation = new Firestation(address, station);
		assertEquals(address, firestation.getAddress());
		assertEquals(station, firestation.getStation());
	}

	@Test
	public void testDefaultConstructor() {
		Firestation firestation = new Firestation();
		assertEquals(null, firestation.getAddress());
		assertEquals(0, firestation.getStation());
	}

	@Test
	public void testSetters() {
		String address = "123 Street";
		int station = 1;
		Firestation firestation = new Firestation();
		firestation.setAddress(address);
		firestation.setStation(station);
		assertEquals(address, firestation.getAddress());
		assertEquals(station, firestation.getStation());
	}

    
	@Test
	public void testToString() {
		String address = "123 Street";
		int station = 1;
		Firestation firestation = new Firestation(address, station);
		String expectedString = "Firestation(address=123 Street, station=1)";
		assertEquals(expectedString, firestation.toString());
	}

	@Test
	public void testHashCode() {
		String address = "123 Street";
		int station = 1;
		Firestation firestation1 = new Firestation(address, station);
		Firestation firestation2 = new Firestation(address, station);
		assertEquals(firestation1.hashCode(), firestation2.hashCode());
	}
	
	@Test
	public void testHashCodeNOK() {
		String address = "123 Street";
		int station = 1;
		Firestation firestation1 = new Firestation(address, station);
		Firestation firestation3 = new Firestation(address, 2);
		assertNotEquals(firestation1.hashCode(), firestation3.hashCode());
	}

	@Test
	public void testEquals() {
		String address1 = "123 Street";
		int station1 = 1;
		Firestation firestation1 = new Firestation(address1, station1);

		String address2 = "123 Street";
		int station2 = 1;
		Firestation firestation2 = new Firestation(address2, station2);

		String address3 = "456 Street";
		int station3 = 2;
		Firestation firestation3 = new Firestation(address3, station3);

		assertEquals(firestation1, firestation2);
		assertTrue(firestation1.getAddress().equals(firestation2.getAddress()));
		assertTrue(firestation1.getStation() == (firestation2.getStation()));
		assertFalse(firestation1.getAddress().equals(firestation3.getAddress()));
		assertNotEquals(firestation1, firestation3);

	}
	
	@Test
	public void testEqualsWithNull() {
	Firestation firestation = new Firestation("123 Street", 1);
	assertFalse(firestation.equals(null));
	}

	@Test
	public void testEqualsWithSameObject() {
	Firestation firestation = new Firestation("123 Street", 1);
	assertTrue(firestation.equals(firestation));
	}
	
	@Test
	public void testEqualsWithDifferentType() {
	Firestation firestation = new Firestation("123 Street", 1);
	Object obj = new Object();
	assertNotEquals(firestation, obj);
	}
	
    @Test
    public void FirestationHashCodeTest() {

        Firestation firestationTest = new Firestation();
        int code = firestationTest.hashCode();

        assertNotEquals(0, code);

    }

}

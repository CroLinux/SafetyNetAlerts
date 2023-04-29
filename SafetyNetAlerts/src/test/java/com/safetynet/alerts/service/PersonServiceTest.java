package com.safetynet.alerts.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL2ResponseFields;
import com.safetynet.alerts.model.URL4ResponseFields;
import com.safetynet.alerts.model.URL5ResponseFields;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.repository.WriteJSONOutputFile;

public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private WriteJSONOutputFile writeJSONOutputFile;

	@InjectMocks
	private PersonService personService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		doNothing().when(writeJSONOutputFile).writeIntoTheFile(anyList());
	}

	@Test
	public void testFindPeopleAndCountByStationNumber() throws IOException {
		// Given
		List<URL1ResponseFields> expected = new ArrayList<>();
		when(personRepository.getPeopleAndCountByStationNumber(anyInt())).thenReturn(expected);
		// When
		List<URL1ResponseFields> result = personService.findPeopleAndCountByStationNumber(1);
		// Then
		assertEquals(result, expected);
		verify(personRepository, times(1)).getPeopleAndCountByStationNumber(1);
	}

	@Test
	public void testFindChildListByAddress() throws IOException {
		// Given
		List<URL2ResponseFields> expected = new ArrayList<>();
		when(personRepository.getChildListByAddress(anyString())).thenReturn(expected);
		// When
		List<URL2ResponseFields> result = personService.findChildListByAddress("123 OPCR Street");
		// Then
		assertEquals(result, expected);
		verify(personRepository, times(1)).getChildListByAddress("123 OPCR Street");

	}

	@Test
	public void testFindPhoneByFirestation() throws IOException {
		// Given
		List<String> expected = new ArrayList<>();
		when(personRepository.getPhoneByFirestation(anyInt())).thenReturn(expected);
		// When
		List<String> result = personService.findPhoneByFirestation(1);
		// Then
		assertEquals(result, expected);
		verify(personRepository, times(1)).getPhoneByFirestation(1);

	}

	@Test
	public void testFindFireListByAddress() throws IOException {
		// Given
		List<URL4ResponseFields> expected = new ArrayList<>();
		when(personRepository.getFireListByAddress(anyString())).thenReturn(expected);
		// When
		List<URL4ResponseFields> result = personService.findFireListByAddress("123 OPCR Street");
		// Then
		assertEquals(result, expected);
		verify(personRepository, times(1)).getFireListByAddress("123 OPCR Street");

	}

	@Test
	public void testFindFloodStationsByFirestation() throws IOException {
		// Given
		List<Integer> firestationList = Arrays.asList(1, 2, 3);
		List<URL5ResponseFields> expected = new ArrayList<>();
		when(personRepository.getFloodStationsByFirestation(firestationList)).thenReturn(expected);
		// When
		List<URL5ResponseFields> result = personService.findFloodStationsByFirestation(firestationList);
		// Then
		assertEquals(result, expected);
		verify(personRepository, times(1)).getFloodStationsByFirestation(firestationList);
	}

	@Test
	public void testFindPersonAndMedicalRecordsByFirstNameAndLastName() throws IOException {
		// Given
		String firstName = "John";
		String lastName = "Boyd";
		List<URL6ResponseFields> expected = new ArrayList<>();
		when(personRepository.getPersonAndMedicalRecordsByFirstNameAndLastName(firstName, lastName))
				.thenReturn(expected);
		// When
		List<URL6ResponseFields> result = personService.findPersonAndMedicalRecordsByFirstNameAndLastName(firstName,
				lastName);
		// Then
		assertEquals(result, expected);
		verify(personRepository, times(1)).getPersonAndMedicalRecordsByFirstNameAndLastName(firstName, lastName);
	}

	@Test
	public void testFindEmailByCity() {
		// Given
		String city = "Springfield";
		List<String> expected = Arrays.asList("jane.doe@example.com", "john.smith@example.com");
		when(personRepository.getEmailByCity(city)).thenReturn(expected);
		// When
		List<String> result = personService.findEmailByCity(city);
		// Then
		assertEquals(expected, result);
		verify(personRepository, times(1)).getEmailByCity(city);
	}

}
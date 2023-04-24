package com.safetynet.alerts.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.PersonNamePhoneAgeMedicalRecords;
import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL2ResponseFields;
import com.safetynet.alerts.model.URL4ResponseFields;

import com.safetynet.alerts.model.URL5ResponseFields;

import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.service.PersonService;

@WebMvcTest(URLSRequestedController.class)
public class URLSRequestedControllerTest {

	@MockBean
	private PersonService personService;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
	}

	@Test
	public void testGetFirestationPeople() throws Exception {
		// Given
		int stationNumber = 1;
		List<URL1ResponseFields> result = new ArrayList<>();
		URL1ResponseFields responseFields = new URL1ResponseFields();
		responseFields.setFirstName("Person");
		responseFields.setLastName("Test");
		result.add(responseFields);
		when(personService.findPeopleAndCountByStationNumber(anyInt())).thenReturn(result);

		// When
		mockMvc.perform(get("/firestation").param("stationNumber", String.valueOf(stationNumber))
				.accept(MediaType.APPLICATION_JSON))

				// Then
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].firstName", equalTo("Person")))
				.andExpect(jsonPath("$[0].lastName", equalTo("Test")));
	}

	@Test
	public void testGetChildList() throws Exception {
		// Given
		String address = "123 OPCR Street";
		List<URL2ResponseFields> result = new ArrayList<>();
		URL2ResponseFields responseFields = new URL2ResponseFields();
		responseFields.setFirstName("Person");
		responseFields.setLastName("Test");
		responseFields.setAge("45");
		result.add(responseFields);
		when(personService.findChildListByAddress(anyString())).thenReturn(result);

		// When
		mockMvc.perform(get("/childAlert").param("address", address).accept(MediaType.APPLICATION_JSON))

				// Then
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].firstName", equalTo("Person")))
				.andExpect(jsonPath("$[0].lastName", equalTo("Test")))
				.andExpect(jsonPath("$[0].age", equalTo("45")));
	}

	@Test
	public void testGetPhoneNumber() throws Exception {
		// Given
		int firestation = 1;
		List<String> result = new ArrayList<>();
		result.add("123-456-7890");
		when(personService.findPhoneByFirestation(anyInt())).thenReturn(result);
		// When
		mockMvc.perform(
				get("/phoneAlert").param("firestation", String.valueOf(firestation)).accept(MediaType.APPLICATION_JSON))

				// Then
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0]", equalTo("123-456-7890")));
	}

	@Test
	public void testGetFireList() throws Exception {
		// Given
		String address = "123 OPCR STreet";
		List<URL4ResponseFields> result = new ArrayList<>();
		URL4ResponseFields responseFields = new URL4ResponseFields();
		responseFields.setStation(1);
		responseFields.setFirstName("Person");
		responseFields.setLastName("Test");
		responseFields.setPhone("123-456-7890");
		responseFields.setAge("45");
		result.add(responseFields);
		when(personService.findFireListByAddress(anyString())).thenReturn(result);
		// When
		mockMvc.perform(get("/fire").param("address", address).accept(MediaType.APPLICATION_JSON))

				// Then
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].station", equalTo(1)))
				.andExpect(jsonPath("$[0].firstName", equalTo("Person")))
				.andExpect(jsonPath("$[0].lastName", equalTo("Test")))
				.andExpect(jsonPath("$[0].phone", equalTo("123-456-7890")))
				.andExpect(jsonPath("$[0].age", equalTo("45")));

	}

	@Test
	public void testGetFloodStationsList() throws Exception {
		// Given
		// List<Integer> firestationList = Arrays.asList(1, 2);
		List<URL5ResponseFields> result = new ArrayList<>();
		List<PersonNamePhoneAgeMedicalRecords> residents = new ArrayList<>();
		URL5ResponseFields responseFields1 = new URL5ResponseFields();
		responseFields1.setAddress("123 OPCR Street");
		responseFields1.setResidents(residents);
		result.add(responseFields1);
		when(personService.findFloodStationsByFirestation(anyList())).thenReturn(result);
		// when(personService.findFloodStationsByFirestation(firestationList)).thenReturn(result);

		// When
		mockMvc.perform(get("/flood/stations").param("stations", "1,2").accept(MediaType.APPLICATION_JSON))

				// Then
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].address", equalTo("123 OPCR Street")))
				.andExpect(jsonPath("$[0].residents", empty()));
	}

	@Test
	public void testGetPersonInfo() throws Exception {
		// Given
		String firstName = "John";
		String lastName = "Doe";
		List<URL6ResponseFields> result = new ArrayList<>();
		URL6ResponseFields responseFields1 = new URL6ResponseFields();
		responseFields1.setFirstName("Person");
		responseFields1.setLastName("Test");
		responseFields1.setAddress("123 OPCR Street");
		responseFields1.setAge("45");
		responseFields1.setEmail("persontest@mail.com");
		result.add(responseFields1);
		when(personService.findPersonAndMedicalRecordsByFirstNameAndLastName(anyString(), anyString()))
				.thenReturn(result);
		// When
		mockMvc.perform(get("/personInfo").param("firstName", firstName).param("lastName", lastName)
				.accept(MediaType.APPLICATION_JSON))
				// Then
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].firstName", equalTo("Person")))
				.andExpect(jsonPath("$[0].lastName", equalTo("Test")))
				.andExpect(jsonPath("$[0].address", equalTo("123 OPCR Street")))
				.andExpect(jsonPath("$[0].age", equalTo("45")))
				.andExpect(jsonPath("$[0].email", equalTo("persontest@mail.com")));
	}

	@Test
	public void testGetCommunityEmail() throws Exception {
		// Given
		String city = "Culver";
		List<String> result = new ArrayList<>();
		result.add("person1@test.com");
		result.add("person2@test.com");
		when(personService.findEmailByCity(city)).thenReturn(result);

		// When
		mockMvc.perform(get("/communityEmail").param("city", city).accept(MediaType.APPLICATION_JSON))

				// Then
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0]", equalTo("person1@test.com")))
				.andExpect(jsonPath("$[1]", equalTo("person2@test.com")));
	}

}

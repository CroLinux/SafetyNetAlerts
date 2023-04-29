package com.safetynet.alerts.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;

@SpringBootTest
public class PersonControllerTest {

    private MockMvc mockMvc;
    private PersonService personService;
    private ObjectMapper objectMapper;

    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
    	personRepository = Mockito.mock(PersonRepository.class);
    	personService = new PersonService(personRepository);
        personService = mock(PersonService.class);
        PersonController personController = new PersonController();
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        objectMapper = new ObjectMapper();
    }

    @SuppressWarnings("static-access")// To remove this warning (when(personService.getPersonInDataSource(firstName, lastName)).thenReturn(personList);)
	@Test
    public void testGetPerson() throws Exception {
        // Given
        String firstName = "Person";
        String lastName = "Test";
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        personList.add(person);
        when(personService.getPersonInDataSource(firstName, lastName)).thenReturn(personList);

        // When
        mockMvc.perform(get("/person")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(firstName))
                .andExpect(jsonPath("$[0].lastName").value(lastName));
    }

    @Test
    public void testCreatePerson() throws Exception {
        // Given
        Person person = new Person();
        person.setFirstName("Person");
        person.setLastName("Test");
        String json = objectMapper.writeValueAsString(person);

        // When
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person.getLastName()));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        // Given
        Person person = new Person();
        person.setFirstName("Person");
        person.setLastName("Test");
        String json = objectMapper.writeValueAsString(person);

        // When
        mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person.getLastName()));
    }

    @Test
    public void testDeletePerson() throws Exception {
        // Given
        Person person = new Person();
        person.setFirstName("Person");
        person.setLastName("Test");
        String json = objectMapper.writeValueAsString(person);

        // When
        mockMvc.perform(delete("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())                
                .andExpect(jsonPath("$.firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person.getLastName()));
    }

}

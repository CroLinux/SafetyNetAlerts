package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;

@RestController
public class PersonController {

	private Logger logger = LogManager.getLogger(PersonController.class);

	/**
	 * URL/Endpoint to get information about a person
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/person")
	public List<Person> getPerson(@RequestParam String firstName, @RequestParam String lastName) throws IOException {
		logger.info(
				"Request GET /person, should return person information depending on their FirstName and LastName: {}, {}",
				firstName, lastName);
		List<Person> getPersonResult = PersonService.getPersonInDataSource(firstName, lastName);
		logger.info("Result of the request: {}", getPersonResult);
		return getPersonResult;
	}

	/**
	 * Endpoint to POST(add) a new person
	 * 
	 * @param person
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/person")
	public Person createPerson(@RequestBody Person person) throws IOException {
		logger.info("Command POST /person, add new Person with information: {}", person);
		PersonService.addPersonInDataSource(person);
		logger.info("Person Added: {}, {}", person.getFirstName(), person.getLastName());
		return person;
	}

	/**
	 * Endpoint to PUT(update) a person
	 * 
	 * @param person
	 * @return
	 * @throws IOException
	 */
	@PutMapping("/person")
	public Person updatePerson(@RequestBody Person person) throws IOException {
		logger.info("Command PUT /person, update a Person with information: {}", person);
		PersonService.updatePersonInDataSource(person);
		logger.info("Person Updated: {}, {}", person.getFirstName(), person.getLastName());
		return person;
	}

	/**
	 * Endpoint to DELETE a person
	 * 
	 * @param person
	 * @return
	 * @throws IOException
	 */
	@DeleteMapping("/person")
	public Person deletePerson(@RequestBody Person person) throws IOException {
		logger.info("Command DELETE /person, delete a Person with information: {}", person);
		PersonService.deletePersonInDataSource(person);
		logger.info("Person Deleted: {}, {}", person.getFirstName(), person.getLastName());
		return person;
	}

}

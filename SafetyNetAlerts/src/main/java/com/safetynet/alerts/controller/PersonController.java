package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.ReadJSONFile;
import com.safetynet.alerts.repository.PersonRepository;

@RestController
public class PersonController {

	@Autowired
	private ReadJSONFile readJSONFile;

	@Autowired
	private PersonRepository personRepository;
	/*
	 * @GetMapping("/personInfo") public List<Person> getPersonInfo(@RequestParam
	 * String firstName, @RequestParam String lastName) { List<Person> persons =
	 * null; try { persons = readJSONFile.getPersons(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } List<Person> result =
	 * new ArrayList<>();
	 * 
	 * for (Person person : persons) { if
	 * (person.getFirstName().equalsIgnoreCase(firstName) &&
	 * person.getLastName().equalsIgnoreCase(lastName)) { result.add(person); } }
	 * 
	 * return result; }
	 */

	@GetMapping("/personInfo")
	public List<Person> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName)
			throws IOException {
		// List<Person> persons = readJSONFile.getPersons();
		List<Person> persons = personRepository.getPersons();
		List<Person> result = new ArrayList<>();
		for (Person person : persons) {
			if (person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
				result.add(person);
			}
		}
		return result;
	}

	@GetMapping("/person")
	public List<Person> personsList() throws IOException {
		List<Person> personList = readJSONFile.getPersons();
		return personList;
	}

	@GetMapping("/medical")
	public List<MedicalRecord> medicalList() throws IOException {
		List<MedicalRecord> medicalList = readJSONFile.getMedicalRecords();
		return medicalList;
	}
}

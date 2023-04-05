package com.safetynet.alerts.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;

@RestController
public class URLSRequestedController {

	// URLSRequestedController URLSRC = new URLSRequestedController();

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	@Autowired
	private PersonService personService;

	/*
	 * URL 6 -
	 * http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>. We
	 * should get here: Name - Address - Age - Email - MedicalRecords. We should
	 * provide here: FirstName and LastName
	 */

	@GetMapping("/personInfo2")
	public List<URL6ResponseFields> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName)
			throws IOException {
		// List<Person> persons = readJSONFile.getPersons();
		List<Person> persons = personRepository.getPersons();
		List<MedicalRecord> medicalRecords = medicalRecordRepository.getMedicalRecords();
		List<URL6ResponseFields> result = new LinkedList<URL6ResponseFields>();

		for (Person person : persons) {
			if (person.getFirstName().equals(firstName) && (person.getLastName().equals(lastName))) {
				for (MedicalRecord medicalRecord : medicalRecords) {
					if (medicalRecord.getFirstName().equals(firstName)
							&& (medicalRecord.getLastName().equals(lastName))) {

						// set
						URL6ResponseFields result2 = new URL6ResponseFields();
						result2.setFirstName(person.getFirstName());
						result2.setLastName(person.getLastName());
						result2.setAddress(person.getAddress());
						// result2.setAge(calculateAge(medicalRecord.getBirthdate()));
						// result2.setAge(medicalRecord.getBirthdate());
						result2.setAge(calculateAge(medicalRecord.getBirthdate()));
						result2.setEmail(person.getEmail());
						result2.setMedication(medicalRecord.getMedications());
						result2.setAllergies(medicalRecord.getAllergies());

						result.add(result2);

					}

				}
			}
		}

		/*
		 * for (Person person : persons) { if
		 * (person.getFirstName().equalsIgnoreCase(firstName) &&
		 * person.getLastName().equalsIgnoreCase(lastName)) { result.add(person); } }
		 */

		return result;
	}

	
	
	public static String calculateAge(String dateStringProvided) {
		// Format the String date provided
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate birthDate = LocalDate.parse(dateStringProvided, formatter);

		// Calculate the years
		LocalDate currentDate = LocalDate.now();
		Period age = Period.between(birthDate, currentDate);

		// return in String format the age.getYears();
		return String.valueOf(age.getYears());
	}
	
	@GetMapping("/personInfo3")
	public List<URL6ResponseFields> getPersonInfo3(@RequestParam String firstName, @RequestParam String lastName)
			throws IOException {
		
		List<URL6ResponseFields> resultURL6 = personService.findPersonAndMedicalRecordsByFirstNameAndLastName(firstName, lastName);
		
		return resultURL6;
		/**
		// List<Person> persons = readJSONFile.getPersons();
		List<Person> persons = personRepository.getPersons();
		List<MedicalRecord> medicalRecords = medicalRecordRepository.getMedicalRecords();
		List<URL6ResponseFields> result = new LinkedList<URL6ResponseFields>();

		for (Person person : persons) {
			if (person.getFirstName().equals(firstName) && (person.getLastName().equals(lastName))) {
				for (MedicalRecord medicalRecord : medicalRecords) {
					if (medicalRecord.getFirstName().equals(firstName)
							&& (medicalRecord.getLastName().equals(lastName))) {

						// set
						URL6ResponseFields result2 = new URL6ResponseFields();
						result2.setFirstName(person.getFirstName());
						result2.setLastName(person.getLastName());
						result2.setAddress(person.getAddress());
						// result2.setAge(calculateAge(medicalRecord.getBirthdate()));
						// result2.setAge(medicalRecord.getBirthdate());
						result2.setAge(calculateAge(medicalRecord.getBirthdate()));
						result2.setEmail(person.getEmail());
						result2.setMedication(medicalRecord.getMedications());
						result2.setAllergies(medicalRecord.getAllergies());

						result.add(result2);

					}

				}
			}
		}



		return result;*/
	}
	
}

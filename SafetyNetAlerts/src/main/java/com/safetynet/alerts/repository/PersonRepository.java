package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.utils.Utils;

@Repository
public class PersonRepository {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	private static List<Person> personList = new ArrayList<>();

	public PersonRepository() throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		personList = readJSONFile.getPersons();
	}

	public List<Person> getPersons() {
		return personList;
	}

	public List<URL6ResponseFields> getPersonAndMedicalRecordsByFirstNameAndLastName(String firstName,
			String lastName) {

		List<MedicalRecord> medicalRecords = medicalRecordRepository.getMedicalRecords();
		List<URL6ResponseFields> resultPRURL6 = new LinkedList<URL6ResponseFields>();

		for (Person person : personList) {
			if (person.getFirstName().equals(firstName) && (person.getLastName().equals(lastName))) {
				for (MedicalRecord medicalRecord : medicalRecords) {
					if (medicalRecord.getFirstName().equals(firstName)
							&& (medicalRecord.getLastName().equals(lastName))) {

						// set values
						URL6ResponseFields result2 = new URL6ResponseFields();
						result2.setFirstName(person.getFirstName());
						result2.setLastName(person.getLastName());
						result2.setAddress(person.getAddress());
						result2.setAge(Utils.calculateAge(medicalRecord.getBirthdate()));
						result2.setEmail(person.getEmail());
						result2.setMedication(medicalRecord.getMedications());
						result2.setAllergies(medicalRecord.getAllergies());

						resultPRURL6.add(result2);
					}
				}
			}
		}
		return resultPRURL6;
	}

}

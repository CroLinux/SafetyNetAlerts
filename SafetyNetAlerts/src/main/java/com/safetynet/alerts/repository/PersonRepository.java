package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.utils.Utils;

@Repository
public class PersonRepository {

	@Autowired
	private FirestationRepository firestationRepository;

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	private static List<Person> personList = new ArrayList<>();

	private static List<Firestation> firestationsList = new ArrayList<>();

	private static List<MedicalRecord> medicalRecords = new ArrayList<>();

	// private static List<Firestation> firestationsList = new ArrayList<>();

	public PersonRepository() throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		personList = readJSONFile.getPersons();
	}

	public List<Person> getPersons() {
		return personList;
	}

	public List<URL1ResponseFields> getPeopleAndCountByStationNumber(int stationNumber) {

		firestationsList = firestationRepository.getFirestationsByID(stationNumber);
		medicalRecords = medicalRecordRepository.getMedicalRecords();
		List<URL1ResponseFields> resultPRURL1 = new LinkedList<URL1ResponseFields>();
		int adults1 = 0;
		int kids1 = 0;

		for (Person person : personList) {
			for (Firestation firestation : firestationsList) {
				for (MedicalRecord medicalRecord : medicalRecords) {
					if (firestation.getAddress().equals(person.getAddress())
							&& person.getFirstName().equals(medicalRecord.getFirstName())
							&& person.getLastName().equals(medicalRecord.getLastName())) {
						String ageVerify = Utils.calculateAge(medicalRecord.getBirthdate());
						int ageVerifyInt = Integer.parseInt(ageVerify);
						if (ageVerifyInt >= 18) {
							adults1++;
						} else {
							kids1++;
						}

						// set values
						URL1ResponseFields result1 = new URL1ResponseFields();
						result1.setFirstName(person.getFirstName());
						result1.setLastName(person.getLastName());
						result1.setAddress(person.getAddress());
						result1.setPhone(person.getPhone());
						result1.setAdults(adults1);
						result1.setKids(kids1);

						resultPRURL1.add(result1);
					}
				}
			}

		}

		return resultPRURL1;
	}

	public List<String> getPhoneByFirestation(int firestation) {
		firestationsList = firestationRepository.getFirestationsByID(firestation);

		List<String> resultPRURL3 = new ArrayList<>();
		for (Person person : personList) {
			for (Firestation firestation3 : firestationsList) {
				if (firestation3.getAddress().equals(person.getAddress())) {
					resultPRURL3.add(person.getPhone());
				}
			}
		}
		return resultPRURL3;
	}

	public List<URL6ResponseFields> getPersonAndMedicalRecordsByFirstNameAndLastName(String firstName,
			String lastName) {

		medicalRecords = medicalRecordRepository.getMedicalRecords();
		List<URL6ResponseFields> resultPRURL6 = new LinkedList<URL6ResponseFields>();

		for (Person person : personList) {
			if (person.getFirstName().equals(firstName) && (person.getLastName().equals(lastName))) {
				for (MedicalRecord medicalRecord : medicalRecords) {
					if (medicalRecord.getFirstName().equals(firstName)
							&& (medicalRecord.getLastName().equals(lastName))) {

						// set values
						URL6ResponseFields result6 = new URL6ResponseFields();
						result6.setFirstName(person.getFirstName());
						result6.setLastName(person.getLastName());
						result6.setAddress(person.getAddress());
						result6.setAge(Utils.calculateAge(medicalRecord.getBirthdate()));
						result6.setEmail(person.getEmail());
						result6.setMedication(medicalRecord.getMedications());
						result6.setAllergies(medicalRecord.getAllergies());

						resultPRURL6.add(result6);
					}
				}
			}
		}
		return resultPRURL6;
	}

	public List<String> getEmailByCity(String city) {
		List<String> resultPRURL7 = new ArrayList<>();
		for (Person person : personList) {
			if (person.getCity().equals(city)) {
				resultPRURL7.add(person.getEmail());
			}
		}
		return resultPRURL7;
	}

}

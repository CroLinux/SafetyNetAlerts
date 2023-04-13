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
import com.safetynet.alerts.model.PersonFullData;
import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL2ResponseFields;
import com.safetynet.alerts.model.URL4ResponseFields;
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

	// We extract the person info from the persons part.
	public PersonRepository() throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		personList = readJSONFile.getPersons();
	}

	// We return the table with the small person info.
	public List<Person> getPersons() {
		return personList;
	}

	// We generate a table with all the personal info from the 3 parts of the
	// provided file.
	public List<PersonFullData> getPersonsFullData() {
		List<PersonFullData> getPersonsListFullData = new ArrayList<>();

		medicalRecords = medicalRecordRepository.getMedicalRecords();
		firestationsList = firestationRepository.getFirestations();

		for (Person person : personList) {
			for (Firestation firestation : firestationsList) {
				for (MedicalRecord medicalRecord : medicalRecords) {
					if (firestation.getAddress().equals(person.getAddress())
							&& person.getFirstName().equals(medicalRecord.getFirstName())
							&& person.getLastName().equals(medicalRecord.getLastName())) {

						// set values
						PersonFullData resultFull = new PersonFullData();
						resultFull.setFirstName(person.getFirstName());
						resultFull.setLastName(person.getLastName());
						resultFull.setAddress(person.getAddress());
						resultFull.setCity(person.getCity());
						resultFull.setZip(person.getZip());
						resultFull.setPhone(person.getPhone());
						resultFull.setEmail(person.getEmail());
						resultFull.setBirthdate(medicalRecord.getBirthdate());
						resultFull.setAge(Utils.calculateAge(medicalRecord.getBirthdate()));
						resultFull.setMedications(medicalRecord.getMedications());
						resultFull.setAllergies(medicalRecord.getAllergies());
						resultFull.setStation(firestation.getStation());

						getPersonsListFullData.add(resultFull);
					}
				}
			}
		}
		// System.out.println("FULL : ");
		// System.out.println(getPersonsListFullData);
		return getPersonsListFullData;
	}

	// From the full file generated, we generate a file with adults only, over 18y.
	public List<PersonFullData> getFullAdults() {
		List<PersonFullData> getFullAdultsList = new ArrayList<>();
		List<PersonFullData> personsFullList1 = getPersonsFullData();

		for (PersonFullData person : personsFullList1) {
			int readAgeFullData = Integer.parseInt(person.getAge());
			if (readAgeFullData > 18) {
				// System.out.println("Adults " + readAgeFullData + person.getFirstName() +
				// person.getLastName());
				getFullAdultsList.add(person);
				// System.out.println(getFullAdultsList);
			} else {
				// System.out.println("Kids " + readAgeFullData + person.getFirstName() +
				// person.getLastName());
			}

		}
		return getFullAdultsList;
	}

	// From the full file generated, we generate a file with childs only, 18y and
	// under.
	public List<PersonFullData> getFullChilds() {
		List<PersonFullData> getFullChildsList = new ArrayList<>();
		List<PersonFullData> personsFullList2 = getPersonsFullData();

		for (PersonFullData person : personsFullList2) {
			int readAgeFullData = Integer.parseInt(person.getAge());
			if (readAgeFullData <= 18) {
				// System.out.println("Kids " + readAgeFullData + person.getFirstName() +
				// person.getLastName());
				getFullChildsList.add(person);
				// System.out.println(getFullChildsList);
			} else {
				// System.out.println("Adults " + readAgeFullData + person.getFirstName() +
				// person.getLastName());
			}

		}
		return getFullChildsList;
	}

	// Usefull for the URL 2 request
	public List<Person> getPersonsByAddress(String address) {
		List<Person> getPersonsListByAddress = new ArrayList<>();

		for (Person person : personList) {
			if (person.getAddress().equals(address)) {
				getPersonsListByAddress.add(person);
			}
		}
		return getPersonsListByAddress;
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

	public List<URL2ResponseFields> getChildListByAddress(String address) {
		// medicalRecords = medicalRecordRepository.getMedicalRecords();
		List<URL2ResponseFields> resultPRURL2 = new LinkedList<URL2ResponseFields>();
		List<Person> personListByAddress = getPersonsByAddress(address);
		List<PersonFullData> personFullList = getPersonsFullData();

		for (PersonFullData personfull : personFullList) {
			if ((personfull.getAddress().equals(address)) && (Integer.parseInt(personfull.getAge()) <= 18)) {
				URL2ResponseFields result2Kids = new URL2ResponseFields();
				result2Kids.setFirstName(personfull.getFirstName());
				result2Kids.setLastName(personfull.getLastName());
				result2Kids.setAge(personfull.getAge());

				List<String> withResidents = new ArrayList<>();
				for (Person person : personListByAddress) {
					if (!person.getFirstName().equals(result2Kids.getFirstName())) {
						withResidents.add(person.getFirstName() + " " + person.getLastName());
						result2Kids.setWithResidents(withResidents);
					}
				}
				resultPRURL2.add(result2Kids);
			}
		}
		return resultPRURL2;
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
	
	public List<URL4ResponseFields> getFireListByAddress(String address) {
		List<URL4ResponseFields> resultPRURL4 = new LinkedList<URL4ResponseFields>();
		List<PersonFullData> personFullList = getPersonsFullData();
		
		for (PersonFullData personfull : personFullList) {
			if ((personfull.getAddress().equals(address))) {

				URL4ResponseFields result4 = new URL4ResponseFields();
				result4.setStation(personfull.getStation());
				result4.setFirstName(personfull.getFirstName());
				result4.setLastName(personfull.getLastName());
				result4.setPhone(personfull.getPhone());
				result4.setAge(personfull.getAge());
				result4.setMedications(personfull.getMedications());
				result4.setAllergies(personfull.getAllergies());				

				resultPRURL4.add(result4);				
			}			
		}		
		return resultPRURL4;
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

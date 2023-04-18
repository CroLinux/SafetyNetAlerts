package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonFullData;
import com.safetynet.alerts.model.PersonNamePhoneAgeMedicalRecords;
import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL2ResponseFields;
import com.safetynet.alerts.model.URL4ResponseFields;
import com.safetynet.alerts.model.URL5ResponseFields;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.utils.Utils;

@Repository
public class PersonRepository {

	@Autowired
	private FirestationRepository firestationRepository;

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@Autowired
	private CRUDOnJSONFile crudOnJSONFile;

	// @Autowired
	// private PersonRepository personRepository;

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

	/**
	 * GET http://localhost:8080/person (this one is not requested)
	 * 
	 * @param firstName
	 * @param lastName
	 * @return information for a specific person requested
	 * @throws IOException
	 */
	public List<Person> getPersonInDataSource(String firstName, String lastName) throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		List<Person> persons = readJSONFile.getPersons();
		List<Person> result = new ArrayList<>();
		for (Person getperson : persons) {
			if (getperson.getFirstName().equalsIgnoreCase(firstName)
					&& getperson.getLastName().equalsIgnoreCase(lastName)) {
				result.add(getperson);
			}
		}
		return result;
	}

	// We generate a table with all the personal info from the 3 parts of the
	// provided file.
	public List<PersonFullData> getPersonsFullData() {
		List<PersonFullData> getPersonsListFullData = new ArrayList<>();
		// Set<PersonFullData> getPersonsListFullData = new HashSet<>();

		medicalRecords = medicalRecordRepository.getMedicalRecords();
		firestationsList = firestationRepository.getFirestations();

		for (Person person : personList) {
			for (Firestation firestation : firestationsList) {
				for (MedicalRecord medicalRecord : medicalRecords) {
					if (firestation.getAddress().equalsIgnoreCase(person.getAddress())
							&& person.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
							&& person.getLastName().equalsIgnoreCase(medicalRecord.getLastName())) {

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
		/**
		 * We do a distinct in order to avoid full duplicate values Some residents
		 * depend on many station Ids
		 */

		List<PersonFullData> distinctPersonsListFullData = getPersonsListFullData.stream().distinct()
				.collect(Collectors.toList());
		return distinctPersonsListFullData;
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
			if (person.getAddress().equalsIgnoreCase(address)) {
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
			if ((personfull.getAddress().equalsIgnoreCase(address)) && (Integer.parseInt(personfull.getAge()) <= 18)) {
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
			if ((personfull.getAddress().equalsIgnoreCase(address))) {

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

	public List<URL5ResponseFields> getFloodStationsByFirestation(List<Integer> firestationList) {
		List<URL5ResponseFields> resultPRURL5 = new LinkedList<URL5ResponseFields>();

		for (Integer i : firestationList) {
			HashSet<String> addressURL5 = firestationRepository.getFirestationsAddressByID(i);

			for (String addressURL5bis : addressURL5) {
				URL5ResponseFields result5 = new URL5ResponseFields();
				result5.setAddress(addressURL5bis);

				List<PersonFullData> personFullList = getPersonsFullData();

				// We create a temporary reference table to save all the next resident list
				// which will be created
				HashSet<PersonNamePhoneAgeMedicalRecords> residentsList = new HashSet<PersonNamePhoneAgeMedicalRecords>();

				for (PersonFullData personFull5 : personFullList) {
					if (personFull5.getAddress().equals(addressURL5bis)) {
						PersonNamePhoneAgeMedicalRecords resident = new PersonNamePhoneAgeMedicalRecords();
						resident.setFirstName(personFull5.getFirstName());
						resident.setLastName(personFull5.getLastName());
						resident.setPhone(personFull5.getPhone());
						resident.setAge(personFull5.getAge());
						resident.setAllergies(personFull5.getAllergies());
						resident.setMedications(personFull5.getMedications());

						// We verify if this found resident is already into the temporary table
						if (!residentsList.contains(resident)) {
							// if not, we set it into the result5 table
							result5.setResidents(resident);
							// We add it into the temporary table
							residentsList.add(resident);
						}
					}
				}
				resultPRURL5.add(result5);
			}
		}
		return resultPRURL5;
	}

	public List<URL6ResponseFields> getPersonAndMedicalRecordsByFirstNameAndLastName(String firstName,
			String lastName) {

		// medicalRecords = medicalRecordRepository.getMedicalRecords();
		List<URL6ResponseFields> resultPRURL6 = new LinkedList<URL6ResponseFields>();
		List<PersonFullData> personFullList6 = getPersonsFullData();

		// As requested, We add the firstName as optional, and use the PersonFullList
		// extract and we should distinct the result as some person depend of many
		// station Ids
		for (PersonFullData personFull6 : personFullList6) {
			if ((firstName == null || firstName.isEmpty() || personFull6.getFirstName().equalsIgnoreCase(firstName))
					&& personFull6.getLastName().equalsIgnoreCase(lastName)) {

				URL6ResponseFields result6 = new URL6ResponseFields();
				result6.setFirstName(personFull6.getFirstName());
				result6.setLastName(personFull6.getLastName());
				result6.setAddress(personFull6.getAddress());
				result6.setAge(personFull6.getAge());
				result6.setEmail(personFull6.getEmail());
				result6.setMedication(personFull6.getMedications());
				result6.setAllergies(personFull6.getAllergies());

				resultPRURL6.add(result6);
			}
		}

		List<URL6ResponseFields> distinctresultPRURL6 = resultPRURL6.stream().distinct().collect(Collectors.toList());
		return distinctresultPRURL6;
	}

	public List<String> getEmailByCity(String city) {
		List<String> resultPRURL7 = new ArrayList<>();
		for (Person person : personList) {
			if (person.getCity().equalsIgnoreCase(city)) {
				resultPRURL7.add(person.getEmail());
			}
		}
		return resultPRURL7;
	}

	/**
	 * 
	 * --- ENDPOINTS FOR PERSON ---
	 * 
	 */

	public List<Person> addPersonInDataSource(Person person) throws IOException {
		// Verify if the same person is not into the file
		if (!crudOnJSONFile.isPersonAlreadyExists(person)) {
			return null;
		}
		// Read the file content into a string
		JsonNode rootNode = crudOnJSONFile.readJsonFile("src/main/resources/data.json");
		// Add the new person into it
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode personNode = objectMapper.convertValue(person, ObjectNode.class);
		((ArrayNode) rootNode.get("persons")).add(personNode);
		// Write the string into the file
		crudOnJSONFile.writeJsonFile("src/main/resources/data.json", rootNode);
		return null;
	}

	public List<Person> updatePersonInDataSource(Person person) throws IOException {
		// Verify if the person exist in the file
		if (crudOnJSONFile.isPersonAlreadyExists(person)) {
			// Read the file content into a string
			JsonNode rootNode = crudOnJSONFile.readJsonFile("src/main/resources/data.json");
			// Search for the given person
			JsonNode personNode = crudOnJSONFile.findPersonNode(rootNode, person);
			if (personNode != null) {
				// Update the person info
				((ObjectNode) personNode).put("firstName", person.getFirstName());
				((ObjectNode) personNode).put("lastName", person.getLastName());
				((ObjectNode) personNode).put("address", person.getAddress());
				((ObjectNode) personNode).put("city", person.getCity());
				((ObjectNode) personNode).put("zip", person.getZip());
				((ObjectNode) personNode).put("phone", person.getPhone());
				((ObjectNode) personNode).put("email", person.getEmail());
				// Write the string into the file
				crudOnJSONFile.writeJsonFile("src/main/resources/data.json", rootNode);
				return null;
			}
		}
		return null;
	}

	public List<Person> deletePersonInDataSource(Person person) throws IOException {
		// Verify if the person exist in the file
		if (crudOnJSONFile.isPersonAlreadyExists(person)) {
			// Read the file content into a string and get the persons array/table
			JsonNode rootNode = crudOnJSONFile.readJsonFile("src/main/resources/data.json");
			ArrayNode personsArray = (ArrayNode) rootNode.get("persons");
			// Search for the given person and delete it
			ObjectMapper objectMapper = new ObjectMapper();
			for (int i = 0; i < personsArray.size(); i++) {
				JsonNode node = personsArray.get(i);
				Person p = objectMapper.treeToValue(node, Person.class);
				if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
					personsArray.remove(i);
					// Write the string into the file
					crudOnJSONFile.writeJsonFile("src/main/resources/data.json", rootNode);
					return null;
				}
			}
		}
		return null;
	}

}

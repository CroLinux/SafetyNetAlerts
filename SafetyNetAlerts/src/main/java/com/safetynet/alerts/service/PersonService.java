package com.safetynet.alerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL2ResponseFields;
import com.safetynet.alerts.model.URL4ResponseFields;
import com.safetynet.alerts.model.URL5ResponseFields;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.repository.WriteJSONOutputFile;

@Service
public class PersonService {

	@Autowired
	private static PersonRepository personRepository;
	
	@Autowired
	private WriteJSONOutputFile writeJSONOutputFile;

	public PersonService(PersonRepository personRepository) {
		PersonService.personRepository = personRepository;
		this.writeJSONOutputFile = new WriteJSONOutputFile();
	}


	public List<URL1ResponseFields> findPeopleAndCountByStationNumber(int stationNumber) throws IOException {
		List<URL1ResponseFields> resultPSURL1 = personRepository.getPeopleAndCountByStationNumber(stationNumber);
		writeJSONOutputFile.writeIntoTheFile(resultPSURL1);
		return resultPSURL1;
	}

	public List<URL2ResponseFields> findChildListByAddress(String address) throws IOException {
		List<URL2ResponseFields> resultPSURL2 = personRepository.getChildListByAddress(address);
		writeJSONOutputFile.writeIntoTheFile(resultPSURL2);
		return resultPSURL2;
	}

	public List<String> findPhoneByFirestation(int firestation) throws IOException {
		List<String> resultPSURL3 = personRepository.getPhoneByFirestation(firestation);
		writeJSONOutputFile.writeIntoTheFile(resultPSURL3);
		return resultPSURL3;
	}

	public List<URL4ResponseFields> findFireListByAddress(String address) throws IOException {
		List<URL4ResponseFields> resultPSURL4 = personRepository.getFireListByAddress(address);
		writeJSONOutputFile.writeIntoTheFile(resultPSURL4);
		return resultPSURL4;
	}

	public List<URL5ResponseFields> findFloodStationsByFirestation(List<Integer> firestationList) throws IOException {
		List<URL5ResponseFields> resultPSURL5 = personRepository.getFloodStationsByFirestation(firestationList);
		writeJSONOutputFile.writeIntoTheFile(resultPSURL5);
		return resultPSURL5;
	}

	public List<URL6ResponseFields> findPersonAndMedicalRecordsByFirstNameAndLastName(String firstName,
			String lastName) throws IOException {
		List<URL6ResponseFields> resultPSURL6 = personRepository
				.getPersonAndMedicalRecordsByFirstNameAndLastName(firstName, lastName);
		writeJSONOutputFile.writeIntoTheFile(resultPSURL6);
		return resultPSURL6;
	}

	public List<String> findEmailByCity(String city) {
		List<String> resultPSURL7 = personRepository.getEmailByCity(city);
		writeJSONOutputFile.writeIntoTheFile(resultPSURL7);
		return resultPSURL7;
	}

	/**
	 * 
	 * --- ENDPOINTS FOR PERSON ---
	 * 
	 */

	public static List<Person> getPersonInDataSource(String firstName, String lastName) throws IOException {
		List<Person> getPersonInDataSource = personRepository.getPersonInDataSource(firstName, lastName);
		return getPersonInDataSource;
	}

	public static List<Person> addPersonInDataSource(Person person) throws IOException {
		List<Person> personList = personRepository.addPersonInDataSource(person);
		return personList;
	}

	public static List<Person> updatePersonInDataSource(Person person) throws IOException {
		List<Person> personList = personRepository.updatePersonInDataSource(person);
		return personList;
	}

	public static List<Person> deletePersonInDataSource(Person person) throws IOException {
		List<Person> personList = personRepository.deletePersonInDataSource(person);
		return personList;
	}

}

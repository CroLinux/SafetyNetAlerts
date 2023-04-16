package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL2ResponseFields;
import com.safetynet.alerts.model.URL4ResponseFields;
import com.safetynet.alerts.model.URL5ResponseFields;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.repository.WriteJSONFile;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private WriteJSONFile writeJSONFile;

	public List<URL1ResponseFields> findPeopleAndCountByStationNumber(int stationNumber) {
		List<URL1ResponseFields> resultPSURL1 = personRepository.getPeopleAndCountByStationNumber(stationNumber);
		writeJSONFile.writeIntoTheFile(resultPSURL1);
		return resultPSURL1;
	}

	public List<URL2ResponseFields> findChildListByAddress(String address) {
		List<URL2ResponseFields> resultPSURL2 = personRepository.getChildListByAddress(address);
		writeJSONFile.writeIntoTheFile(resultPSURL2);
		return resultPSURL2;
	}

	public List<String> findPhoneByFirestation(int firestation) {
		List<String> resultPSURL3 = personRepository.getPhoneByFirestation(firestation);
		writeJSONFile.writeIntoTheFile(resultPSURL3);
		return resultPSURL3;
	}

	public List<URL4ResponseFields> findFireListByAddress(String address) {
		List<URL4ResponseFields> resultPSURL4 = personRepository.getFireListByAddress(address);
		writeJSONFile.writeIntoTheFile(resultPSURL4);
		return resultPSURL4;
	}

	public List<URL5ResponseFields> findFloodStationsByFirestation(List<Integer> firestationList) {
		List<URL5ResponseFields> resultPSURL5 = personRepository.getFloodStationsByFirestation(firestationList);
		writeJSONFile.writeIntoTheFile(resultPSURL5);
		return resultPSURL5;
	}

	public List<URL6ResponseFields> findPersonAndMedicalRecordsByFirstNameAndLastName(String firstName,
			String lastName) {
		List<URL6ResponseFields> resultPSURL6 = personRepository
				.getPersonAndMedicalRecordsByFirstNameAndLastName(firstName, lastName);
		writeJSONFile.writeIntoTheFile(resultPSURL6);
		return resultPSURL6;
	}

	public List<String> findEmailByCity(String city) {
		List<String> resultPSURL7 = personRepository.getEmailByCity(city);
		writeJSONFile.writeIntoTheFile(resultPSURL7);
		return resultPSURL7;
	}

}

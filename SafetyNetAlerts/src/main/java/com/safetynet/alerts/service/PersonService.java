package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public List<URL1ResponseFields> findPeopleAndCountByStationNumber(int stationNumber) {
		List<URL1ResponseFields> resultPSURL1 = personRepository.getPeopleAndCountByStationNumber(stationNumber);
		return resultPSURL1;
	}

	public List<URL6ResponseFields> findPersonAndMedicalRecordsByFirstNameAndLastName(String firstName,
			String lastName) {
		List<URL6ResponseFields> resultPSURL6 = personRepository
				.getPersonAndMedicalRecordsByFirstNameAndLastName(firstName, lastName);

		return resultPSURL6;
	}

	public List<String> findEmailByCity(String city) {
		List<String> resultPSURL7 = personRepository.getEmailByCity(city);
		return resultPSURL7;
	}

}

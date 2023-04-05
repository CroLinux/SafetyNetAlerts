package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;

	public List<URL6ResponseFields> findPersonAndMedicalRecordsByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		
		List<URL6ResponseFields> resultPSURL6 = personRepository.getPersonAndMedicalRecordsByFirstNameAndLastName(firstName, lastName) ;
		
		
		return resultPSURL6;
	}

}

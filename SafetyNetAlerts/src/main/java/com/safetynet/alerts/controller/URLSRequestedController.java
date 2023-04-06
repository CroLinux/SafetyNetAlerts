package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.service.PersonService;

@RestController
public class URLSRequestedController {

	@Autowired
	private PersonService personService;

	/**
	 * URL 1 - http://localhost:8080/firestation?stationNumber=<station_number> We
	 * should get here: Name - Address - Phone - number of Adults - number of Kids;
	 * We have to provide here: station_number
	 */
	@GetMapping("/firestation")
	public List<URL1ResponseFields> getFirestationPeople(@RequestParam int stationNumber) throws IOException {
		List<URL1ResponseFields> resultURL1 = personService.findPeopleAndCountByStationNumber(stationNumber);
		return resultURL1;
	}

	/**
	 * URL 3 - http://localhost:8080/phoneAlert?firestation=<firestation_number> We
	 * should get here all the phone numbers of the residents from a specified
	 * firestation number We have to provide here: station_number
	 */
	@GetMapping("/phoneAlert")
	public List<String> getPhoneNumber(@RequestParam int firestation) throws IOException {
		List<String> resultURL3 = personService.findPhoneByFirestation(firestation);
		return resultURL3;

	}

	/**
	 * URL 6 -
	 * http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>.
	 * We should get here: Name - Address - Age - Email - MedicalRecords. We have to
	 * provide here: FirstName and LastName
	 */
	@GetMapping("/personInfo")
	public List<URL6ResponseFields> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName)
			throws IOException {

		List<URL6ResponseFields> resultURL6 = personService.findPersonAndMedicalRecordsByFirstNameAndLastName(firstName,
				lastName);
		return resultURL6;
	}

	/**
	 * URL 7 - http://localhost:8080/communityEmail?city=<city> We should get here
	 * all the emails from all the residents of the city. We have to provide here:
	 * City
	 */
	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam String city) throws IOException {

		List<String> resultURL7 = personService.findEmailByCity(city);
		return resultURL7;

	}

}

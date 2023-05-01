package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.URL1ResponseFields;
import com.safetynet.alerts.model.URL2ResponseFields;
import com.safetynet.alerts.model.URL4ResponseFields;
import com.safetynet.alerts.model.URL5ResponseFields;
import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.service.PersonService;

@RestController
public class URLSRequestedController {

	private Logger logger = LogManager.getLogger(URLSRequestedController.class);

	@Autowired
	private PersonService personService;

	/**
	 * URL 1 - http://localhost:8080/firestation?stationNumber=<station_number> We
	 * should get here: Name - Address - Phone - number of Adults - number of Kids;
	 * We have to provide here: station_number
	 */
	@GetMapping("/firestation")
	public List<URL1ResponseFields> getFirestationPeople(@RequestParam("stationNumber") int stationNumber)
			throws IOException {
		logger.info("Request GET /firestation, should return a persons list depending on the Station Number: {}",
				stationNumber);
		List<URL1ResponseFields> resultURL1 = personService.findPeopleAndCountByStationNumber(stationNumber);
		logger.info("Result of the request: {}", resultURL1);
		return resultURL1;
	}

	/**
	 * URL 2 - http://localhost:8080/childAlert?address=<address> We should get a
	 * list of kids (<=18 y.) with Name - Age + List of the other residents from
	 * specified address. We should provide here: addresss
	 */
	@GetMapping("/childAlert")
	public List<URL2ResponseFields> getChildList(@RequestParam("address") String address) throws IOException {
		logger.info(
				"Request GET /childAlert, should return a childs list with their residents depending on the address: {}",
				address);
		List<URL2ResponseFields> resultURL2 = personService.findChildListByAddress(address);
		logger.info("Result of the request: {}", resultURL2);
		return resultURL2;
	}

	/**
	 * URL 3 - http://localhost:8080/phoneAlert?firestation=<firestation_number> We
	 * should get here all the phone numbers of the residents from a specified
	 * firestation number We have to provide here: station_number
	 */
	@GetMapping("/phoneAlert")
	public List<String> getPhoneNumber(@RequestParam("firestation") int firestation) throws IOException {
		logger.info("Request GET /phoneAlert, should return a phone numbers list depending on the firestation: {}",
				firestation);
		List<String> resultURL3 = personService.findPhoneByFirestation(firestation);
		logger.info("Result of the request: {}", resultURL3);
		return resultURL3;
	}

	/**
	 * URL 4 - http://localhost:8080/fire?address=<address> We should get here
	 * station Id, Name, Tel, Age and Medical records from specified address. We
	 * have to provide the address
	 */
	@GetMapping("/fire")
	public List<URL4ResponseFields> getFireList(@RequestParam("address") String address) throws IOException {
		logger.info("Request GET /fire, should return a residents list depending on the address: {}", address);
		List<URL4ResponseFields> resultURL4 = personService.findFireListByAddress(address);
		logger.info("Result of the request: {}", resultURL4);
		return resultURL4;
	}

	/**
	 * URL 5 - http://localhost:8080/flood/stations?stations=<a list of
	 * station_numbers> We should get a list grouped by address and get name, phone,
	 * age and Medical records from specified station Id(s). We should provide the
	 * station Id(s)
	 */
	@GetMapping("/flood/stations")
	public List<URL5ResponseFields> getFloodStationsList(@RequestParam("stations") List<Integer> firestationList)
			throws IOException {
		logger.info(
				"Request GET /flood/stations, should return a residents list with their informations depending on the station(s): {}",
				firestationList);
		List<URL5ResponseFields> resultURL5 = personService.findFloodStationsByFirestation(firestationList);
		logger.info("Result of the request: {}", resultURL5);
		return resultURL5;
	}

	/**
	 * URL 6 -
	 * http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>.
	 * We should get here: Name - Address - Age - Email - MedicalRecords. We have to
	 * provide here: FirstName and LastName
	 */
	@GetMapping("/personInfo")
	public List<URL6ResponseFields> getPersonInfo(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) throws IOException {
		logger.info(
				"Request GET /personInfo, should return a persons list with their informations depending on their FirstName and LastName: {}, {}",
				firstName, lastName);
		List<URL6ResponseFields> resultURL6 = personService.findPersonAndMedicalRecordsByFirstNameAndLastName(firstName,
				lastName);
		logger.info("Result of the request: {}", resultURL6);
		return resultURL6;
	}

	/**
	 * URL 7 - http://localhost:8080/communityEmail?city=<city> We should get here
	 * all the emails from all the residents of the city. We have to provide here:
	 * City
	 */
	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam("city") String city) throws IOException {
		logger.info("Request GET /communityEmail, should return an emails list depending on the city: {}", city);
		List<String> resultURL7 = personService.findEmailByCity(city);
		logger.info("Result of the request: {}", resultURL7);
		return resultURL7;
	}
}

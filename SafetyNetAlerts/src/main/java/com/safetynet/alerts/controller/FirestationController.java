package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.FirestationService;

@RestController
public class FirestationController {

	private Logger logger = LogManager.getLogger(FirestationController.class);

	/**
	 * URL/Endpoint To get the firestation information for a specific Station Number
	 * 
	 * @param stationNumber
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/firestationOnly")
	public List<Firestation> getFirestation(@RequestParam("stationNumber") int stationNumber) throws IOException {
		logger.info(
				"Request GET /firestationOnly, should return firestation information depending on the Station Number: {}",
				stationNumber);
		List<Firestation> getFirestationList = FirestationService.getFirestationInDataSource(stationNumber);
		logger.info("Result of the request: {}", getFirestationList);
		return getFirestationList;
	}

	/**
	 * Endpoint to add a new Firestation
	 * 
	 * @param firestation
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/firestation")
	public Firestation createFirestation(@RequestBody Firestation firestation) throws IOException {
		logger.info("Command POST /firestation, add a Firestation with information: {}", firestation);
		FirestationService.addFirestationInDataSource(firestation);
		logger.info("Firestation Added for: {}", firestation.getAddress());
		return firestation;
	}

	/**
	 * Endpoint to update an existing Firestation
	 * 
	 * @param firestation
	 * @return
	 * @throws IOException
	 */
	@PutMapping("/firestation")
	public Firestation updateFirestation(@RequestBody Firestation firestation) throws IOException {
		logger.info("Command PUT /firestation, update a Firestation with information: {}", firestation);
		FirestationService.updateFirestationInDataSource(firestation);
		logger.info("Firestation Updated for: {}", firestation.getAddress());
		return firestation;
	}

	/**
	 * Endpoint to delete an existing Firestation
	 * 
	 * @param firestation
	 * @return
	 * @throws IOException
	 */
	@DeleteMapping("/firestation")
	public Firestation deleteFirestation(@RequestBody Firestation firestation) throws IOException {
		logger.info("Command DELETE /firestation, delete a Firestation with information: {}", firestation);
		FirestationService.deleteFirestationInDataSource(firestation);
		logger.info("Firestation Deleted for: {}", firestation.getAddress());
		return firestation;
	}

}

package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.List;

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
	
	@GetMapping("/firestationOnly")
	public List<Firestation> getFirestation(@RequestParam("stationNumber") int stationNumber) throws IOException {
		List<Firestation> getFirestationList = FirestationService.getFirestationInDataSource(stationNumber);
		return getFirestationList;
	}

	@PostMapping("/firestation")
	public Firestation createFirestation(@RequestBody Firestation firestation) throws IOException {
		System.out.print("create firestation " + firestation);
		FirestationService.addFirestationInDataSource(firestation);
		System.out.print("create firestation2");
		return firestation;
	}

	@PutMapping("/firestation")
	public Firestation updateFirestation(@RequestBody Firestation firestation) throws IOException {
		FirestationService.updateFirestationInDataSource(firestation);
		return firestation;
	}

	@DeleteMapping("/firestation")
	public Firestation deleteFirestation(@RequestBody Firestation firestation) throws IOException {
		FirestationService.deleteFirestationInDataSource(firestation);
		return firestation;
	}

}

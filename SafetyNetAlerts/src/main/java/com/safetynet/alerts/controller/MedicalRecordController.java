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

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	private Logger logger = LogManager.getLogger(MedicalRecordController.class);

	/**
	 * URL/Endpoint To get the medical record for a specific person
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/medicalRecord")
	public List<MedicalRecord> getMedicalRecord(@RequestParam String firstName, @RequestParam String lastName)
			throws IOException {
		logger.info(
				"Request GET /medicalRecord, should return medical record(s) information depending on the FirstName and LastName: {}, {}",
				firstName, lastName);
		List<MedicalRecord> getMedicalRecordResult = MedicalRecordService.getMedicalRecordInDataSource(firstName,
				lastName);
		logger.info("Result of the request: {}", getMedicalRecordResult);
		return getMedicalRecordResult;
	}

	/**
	 * Endpoint to POST(add) a new medical record
	 * 
	 * @param person
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/medicalRecord")
	public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
		logger.info("Command POST /medicalRecord, add new Medical Record with information: {}", medicalRecord);
		MedicalRecordService.addMedicalRecordInDataSource(medicalRecord);
		logger.info("Medical Record(s) Added for: {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
		return medicalRecord;
	}

	/**
	 * Endpoint to PUT(update) a medical record
	 * 
	 * @param person
	 * @return
	 * @throws IOException
	 */
	@PutMapping("/medicalRecord")
	public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
		logger.info("Command PUT /medicalRecord, update a Medical Record with information: {}", medicalRecord);
		MedicalRecordService.updateMedicalRecordInDataSource(medicalRecord);
		logger.info("Medical Record(s) Updated for: {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
		return medicalRecord;
	}

	/**
	 * Endpoint to DELETE a medical
	 * 
	 * @param person
	 * @return
	 * @throws IOException
	 */
	@DeleteMapping("/medicalRecord")
	public MedicalRecord deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
		logger.info("Command DELETE /medicalRecord, delete a Medical Record with information: {}", medicalRecord);
		MedicalRecordService.deleteMedicalRecordInDataSource(medicalRecord);
		logger.info("Medical Record(s) Deleted for: {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
		return medicalRecord;
	}

}

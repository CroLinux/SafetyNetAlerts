package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {

	@Autowired
	private CRUDOnJSONFile crudOnJSONFile;

	private static List<MedicalRecord> medicalRecordList = new ArrayList<>();

	public MedicalRecordRepository() throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		medicalRecordList = readJSONFile.getMedicalRecords();
	}

	public List<MedicalRecord> getMedicalRecords() throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		medicalRecordList = readJSONFile.getMedicalRecords();
		return medicalRecordList;
	}

	public List<MedicalRecord> getMedicalRecordInDataSource(String firstName, String lastName) throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		List<MedicalRecord> medicalRecords = readJSONFile.getMedicalRecords();
		List<MedicalRecord> result = new ArrayList<>();
		for (MedicalRecord getMedicalRecord : medicalRecords) {
			if (getMedicalRecord.getFirstName().equalsIgnoreCase(firstName)
					&& getMedicalRecord.getLastName().equalsIgnoreCase(lastName)) {
				result.add(getMedicalRecord);
			}
		}
		return result;
	}

	public List<MedicalRecord> addMedicalRecordInDataSource(MedicalRecord medicalRecord) throws IOException {
		// Verify if the same medicalRecord is not into the file
		if (!crudOnJSONFile.isMedicalRecordAlreadyExists(medicalRecord)) {
			return null;
		}
		// Read the file content into a string
		JsonNode rootNode = crudOnJSONFile.readJsonFile("src/main/resources/data.json");
		// Add the new medicalRecord into it
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode medicalRecordNode = objectMapper.convertValue(medicalRecord, ObjectNode.class);
		((ArrayNode) rootNode.get("medicalrecords")).add(medicalRecordNode);
		// Write the string into the file
		crudOnJSONFile.writeJsonFile("src/main/resources/data.json", rootNode);
		return null;
	}

	public List<MedicalRecord> updateMedicalRecordInDataSource(MedicalRecord medicalRecord) throws IOException {
		// Verify if the medicalRecord exist in the file
		if (crudOnJSONFile.isMedicalRecordAlreadyExists(medicalRecord)) {
			// Read the file content into a string
			JsonNode rootNode = crudOnJSONFile.readJsonFile("src/main/resources/data.json");
			// Search for the given medicalRecord
			JsonNode medicalRecordNode = crudOnJSONFile.findMedicalRecordNode(rootNode, medicalRecord);
			if (medicalRecordNode != null) {
				// Update the person info
				((ObjectNode) medicalRecordNode).put("firstName", medicalRecord.getFirstName());
				((ObjectNode) medicalRecordNode).put("lastName", medicalRecord.getLastName());
				((ObjectNode) medicalRecordNode).put("birthdate", medicalRecord.getBirthdate());
				// For the medications and allergies lists, we can't add it as an object but as
				// an Array
				ArrayNode medicationsArray = ((ObjectNode) medicalRecordNode).putArray("medications");
				for (String medication : medicalRecord.getMedications()) {
					medicationsArray.add(medication);
				}
				ArrayNode allergiesArray = ((ObjectNode) medicalRecordNode).putArray("allergies");
				for (String allergie : medicalRecord.getAllergies()) {
					allergiesArray.add(allergie);
				}
				// Write the string into the file
				crudOnJSONFile.writeJsonFile("src/main/resources/data.json", rootNode);
				return null;
			}
		}
		return null;
	}

	public List<MedicalRecord> deleteMedicalRecordInDataSource(MedicalRecord medicalRecord) throws IOException {
		// Verify if the medicalRecord exist in the file
		if (crudOnJSONFile.isMedicalRecordAlreadyExists(medicalRecord)) {
			// Read the file content into a string and get the medicalRecord array/table
			JsonNode rootNode = crudOnJSONFile.readJsonFile("src/main/resources/data.json");
			ArrayNode medicalRecordsArray = (ArrayNode) rootNode.get("medicalrecords");
			// Search for the given medicalRecord and delete it
			ObjectMapper objectMapper = new ObjectMapper();
			for (int i = 0; i < medicalRecordsArray.size(); i++) {
				JsonNode node = medicalRecordsArray.get(i);
				MedicalRecord p = objectMapper.treeToValue(node, MedicalRecord.class);
				if (p.getFirstName().equals(medicalRecord.getFirstName()) && p.getLastName().equals(medicalRecord.getLastName())) {
					medicalRecordsArray.remove(i);
					// Write the string into the file
					crudOnJSONFile.writeJsonFile("src/main/resources/data.json", rootNode);
					return null;
				}
			}
		}
		return null;
	}
}

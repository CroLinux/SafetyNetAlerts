package com.safetynet.alerts.repository;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * 
 * @author CroLinux
 * This class is used for the manipulation on a JSON file
 *
 */

@Repository
public class CRUDOnJSONFile {
	
	public JsonNode readJsonFile(String filePath) throws IOException {
	    File file = new File(filePath);
	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode rootNode = objectMapper.readTree(file);
	    return rootNode;
	}


	// Write the given JSON node to a file.
	public boolean writeJsonFile(String filePath, JsonNode rootNode) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    String updatedJsonContent = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
	    Files.writeString(Paths.get(filePath), updatedJsonContent);
	    return true;
	}

	// Search for a JSON node which corresponds to a given person.
	public JsonNode findPersonNode(JsonNode rootNode, Person person) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    ArrayNode personsArray = (ArrayNode) rootNode.get("persons");
	    for (JsonNode node : personsArray) {
	        Person p = objectMapper.treeToValue(node, Person.class);
	        if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
	            return node;
	        }
	    }
	    return null;
	}
	
	// Verify if a person already exists in a JSON file.
	public boolean isPersonAlreadyExists(Person newPerson) throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		List<Person> personsList = readJSONFile.getPersons();
		for (Person person : personsList) {
			if (person.getFirstName().equalsIgnoreCase(newPerson.getFirstName())
					&& person.getLastName().equalsIgnoreCase(newPerson.getLastName())
					&& person.getAddress().equalsIgnoreCase(newPerson.getAddress())
					&& person.getCity().equalsIgnoreCase(newPerson.getCity())
					&& person.getZip().equalsIgnoreCase(newPerson.getZip())
					&& person.getPhone().equalsIgnoreCase(newPerson.getPhone())
					&& person.getEmail().equalsIgnoreCase(newPerson.getEmail())) {
				return false;
			}
		}
		return true;
	}

	// Search for a JSON node which corresponds to a given firestation.
	public JsonNode findFirestationNode(JsonNode rootNode, Firestation firestation) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    ArrayNode firestationsArray = (ArrayNode) rootNode.get("firestations");
	    for (JsonNode node : firestationsArray) {
	        Firestation p = objectMapper.treeToValue(node, Firestation.class);
	        if (p.getAddress().equals(firestation.getAddress())) { 
	        	return node;
	        }
	    }
	    return null;
	}
	
	// Verify if a firestation already exists .
	public boolean isFirestationAlreadyExists(Firestation newFirestation) throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		List<Firestation> firestationsList = readJSONFile.getFirestations();
		for (Firestation firestation : firestationsList) {
			if ((firestation.getAddress().equalsIgnoreCase(newFirestation.getAddress())) 
					&& (firestation.getStation() == newFirestation.getStation())) {
				return false;
			}
		}
		return true;
	}

	// Verify if a Medical Record already exists.
	public boolean isMedicalRecordAlreadyExists(MedicalRecord newMedicalRecord) throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		List<MedicalRecord> medicalRecordsList = readJSONFile.getMedicalRecords();
		for (MedicalRecord medicalRecord : medicalRecordsList) {
			if (medicalRecord.getFirstName().equalsIgnoreCase(newMedicalRecord.getFirstName())
					&& medicalRecord.getLastName().equalsIgnoreCase(newMedicalRecord.getLastName())
					&& medicalRecord.getBirthdate().equalsIgnoreCase(newMedicalRecord.getBirthdate())
					&& medicalRecord.getMedications().equals(newMedicalRecord.getMedications())
					&& medicalRecord.getAllergies().equals(newMedicalRecord.getAllergies())) {
				return false;
			}
		}
		return true;
	}

	// Verify if a Medical Record already exists in a JSON file.
	public JsonNode findMedicalRecordNode(JsonNode rootNode, MedicalRecord medicalRecord) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    ArrayNode medicalRecordsArray = (ArrayNode) rootNode.get("medicalrecords");
	    for (JsonNode node : medicalRecordsArray) {
	        MedicalRecord p = objectMapper.treeToValue(node, MedicalRecord.class);
	        if (p.getFirstName().equals(medicalRecord.getFirstName()) && p.getLastName().equals(medicalRecord.getLastName())) {
	            return node;
	        }
	    }
		return null;
	}
}

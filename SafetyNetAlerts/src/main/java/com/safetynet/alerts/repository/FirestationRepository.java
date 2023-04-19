package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.safetynet.alerts.model.Firestation;

@Repository
public class FirestationRepository {

	@Autowired
	private CRUDOnJSONFile crudOnJSONFile;

	private static List<Firestation> firestationList = new ArrayList<>();

	public FirestationRepository() throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		firestationList = readJSONFile.getFirestations();

	}

	public List<Firestation> getFirestations() {
		return firestationList;
	}

	public List<Firestation> getFirestationsByID(int stationNumber) {
		List<Firestation> firestationListByID = new ArrayList<>();
		for (Firestation firestation : firestationList) {
			if (firestation.getStation() == stationNumber) {
				firestationListByID.add(firestation);
			}

		}
		return firestationListByID;
	}

	public HashSet<String> getFirestationsAddressByID(int stationNumber) {
		HashSet<String> firestationAddressListByID = new HashSet<String>();
		// List<String> firestationAddressListByID = new ArrayList<>();
		for (Firestation firestation : firestationList) {
			if (firestation.getStation() == stationNumber) {
				firestationAddressListByID.add(firestation.getAddress());
			}

		}
		return firestationAddressListByID;
	}

	public List<Firestation> getFirestationInDataSource(int stationNumber) throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		List<Firestation> firestations = readJSONFile.getFirestations();
		List<Firestation> result = new ArrayList<>();
		for (Firestation getFirestation : firestations) {
			if (getFirestation.getStation() == stationNumber) {
				result.add(getFirestation);
			}
		}
		return result;
	}

	public List<Firestation> addFirestationInDataSource(Firestation firestation) throws IOException {
		// Verify if the same firestation mapping is not into the file
		if (!crudOnJSONFile.isFirestationAlreadyExists(firestation)) {
			return null;
		}
		// Read the file content into a string
		JsonNode rootNode = crudOnJSONFile.readJsonFile("src/main/resources/data.json");
		// Add the new firestation mapping into it
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode firestationNode = objectMapper.convertValue(firestation, ObjectNode.class);
		((ArrayNode) rootNode.get("firestations")).add(firestationNode);
		// Write the string into the file
		crudOnJSONFile.writeJsonFile("src/main/resources/data.json", rootNode);
		return null;
	}

	public List<Firestation> updateFirestationInDataSource(Firestation firestation) throws IOException {
		// Verify if the firestation exist in the file
		if (crudOnJSONFile.isFirestationAlreadyExists(firestation)) {
			System.out.println(firestation.getStation());
			// Read the file content into a string
			JsonNode rootNode = crudOnJSONFile.readJsonFile("src/main/resources/data.json");
			// Search for the given firestation
			JsonNode firestationNode = crudOnJSONFile.findFirestationNode(rootNode, firestation);
			System.out.println(firestationNode);
			if (firestationNode != null) {
				// Update the firestation info
				((ObjectNode) firestationNode).put("address", firestation.getAddress());
				System.out.println(firestation.getStation());
				((ObjectNode) firestationNode).put("station", firestation.getStation());
				// Write the string into the file
				System.out.println(firestationNode);
				crudOnJSONFile.writeJsonFile("src/main/resources/data.json", rootNode);
				return null;
			}
		}
		return null;
	}

	public List<Firestation> deleteFirestationInDataSource(Firestation firestation) throws IOException {
		// Verify if the firestation exist in the file
		if (crudOnJSONFile.isFirestationAlreadyExists(firestation)) {
			// Read the file content into a string and get the firestations array/table
			JsonNode rootNode = crudOnJSONFile.readJsonFile("src/main/resources/data.json");
			ArrayNode firestationsArray = (ArrayNode) rootNode.get("firestations");
			// Search for the given firestation and delete "all" occurrences
			ObjectMapper objectMapper = new ObjectMapper();
			boolean firestationdeleted = false;
			for (int i = 0; i < firestationsArray.size(); i++) {
				JsonNode node = firestationsArray.get(i);
				Firestation p = objectMapper.treeToValue(node, Firestation.class);
				if (p.getAddress().equals(firestation.getAddress()) || p.getStation() == firestation.getStation()) {
					firestationsArray.remove(i);
					i--; // reduce the i value in order to continue to read the rest of the array
					firestationdeleted = true;
				}
			}
			if (firestationdeleted) {
				// Write the string into the file
				crudOnJSONFile.writeJsonFile("src/main/resources/data.json", rootNode);
			}
		}
		return null;
	}

}

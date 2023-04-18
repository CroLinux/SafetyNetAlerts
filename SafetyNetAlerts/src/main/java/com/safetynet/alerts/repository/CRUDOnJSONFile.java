package com.safetynet.alerts.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.safetynet.alerts.model.Person;

/**
 * 
 * @author CroLinux
 * This class is used for the manipulation on a JSON file
 *
 */

@Repository
public class CRUDOnJSONFile {
	
	// Read and return the contents of a JSON file.
	public JsonNode readJsonFile(String filePath) throws IOException {
	    String jsonContent = Files.readString(Paths.get(filePath));
	    ObjectMapper objectMapper = new ObjectMapper();
	    return objectMapper.readTree(jsonContent);
	}

	// Write the given JSON node to a file.
	public boolean writeJsonFile(String filePath, JsonNode rootNode) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    String updatedJsonContent = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
	    Files.writeString(Paths.get(filePath), updatedJsonContent);
	    return true;
	}

	// Search for a JSON node which corresponds to a given person.
	public JsonNode findPersonNode(JsonNode rootNode, Person person) throws JsonProcessingException, IllegalArgumentException {
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
}

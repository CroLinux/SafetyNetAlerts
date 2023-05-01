package com.safetynet.alerts.repository;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

/**
 * This class is used to read the provided file and return some standard value(s)
 * 
 * @author CroLinux
 *
 */

@Repository
public class ReadJSONFile {
	
	private Logger logger = LogManager.getLogger(WriteJSONOutputFile.class);
	
		private static Any buffer;

	public ReadJSONFile() throws IOException {
		String filePath = "src/main/resources/data.json";
		//byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());
		//JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
		//buffer = jsonIterator.readAny();
	    try {
	        byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());
	        JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
	        buffer = jsonIterator.readAny();
	    } catch (NoSuchFileException e) {
	    	logger.error("Error with the JSON File: " + filePath, e);
	        //throw new IOException("Le fichier spécifié n'a pas été trouvé : " + filePath);
	    }
	}

	public List<Person> getPersons() {
		List<Person> persons = new ArrayList<>();
		Any personAny = buffer.get("persons");
		personAny.forEach(a -> persons.add(new Person.PersonBuilder().firstName(a.get("firstName").toString())
				.address(a.get("address").toString()).city(a.get("city").toString())
				.lastName(a.get("lastName").toString()).phone(a.get("phone").toString()).zip(a.get("zip").toString())
				.email(a.get("email").toString()).build()));
		return persons;
	}

	private List<String> getList(Any buffer) {

		List<String> medicationsList = new ArrayList<>();
		if (buffer.size() < 0) {
			return new ArrayList<>();
		}
		buffer.forEach(medication -> medicationsList.add(medication.toString()));
		return medicationsList;
	}

	public List<MedicalRecord> getMedicalRecords() {

		List<MedicalRecord> medicalRecordsList = new ArrayList<>();

		Any readMedicalRecords = buffer.get("medicalrecords");
		readMedicalRecords.forEach(a -> medicalRecordsList
				.add(new MedicalRecord(a.get("firstName").toString(), a.get("lastName").toString(),
						a.get("birthdate").toString(), getList(a.get("medications")), getList(a.get("allergies")))));
		return medicalRecordsList;
	}
	
	public List<Firestation> getFirestations() {
		
		List<Firestation> firestationsList = new ArrayList<>();
		
		Any readFirestations = buffer.get("firestations");
		readFirestations.forEach(a -> firestationsList.add(new Firestation(a.get("address").toString(), a.get("station").toInt())));
		return firestationsList;		
	}

}

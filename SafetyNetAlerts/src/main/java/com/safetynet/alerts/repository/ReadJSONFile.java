package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

@Repository
public class ReadJSONFile {

	private static Any buffer;

	// public static void main(String[] args) throws IOException {
	public ReadJSONFile() throws IOException {
		String filePath = "src/main/resources/data.json";
		byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());
		JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
		buffer = jsonIterator.readAny();
	}

	public List<Person> getPersons() {
		List<Person> persons = new ArrayList<>();
		Any personAny = buffer.get("persons");
		personAny.forEach(a -> persons.add(new Person.PersonBuilder().firstName(a.get("firstName").toString())
				.address(a.get("address").toString()).city(a.get("city").toString())
				.lastName(a.get("lastName").toString()).phone(a.get("phone").toString()).zip(a.get("zip").toString())
				.email(a.get("email").toString()).build()));
		//System.out.println(buffer);
		
		// persons.forEach(p -> System.out.println(
		// p.firstName.concat(p.lastName).concat(p.address).concat(p.city).concat(p.phone).concat(p.zip)));

		/*
		 * MedicalRecordsRepository medicalRecordsRepository = new
		 * MedicalRecordsRepository();
		 * 
		 * Any readPersons = buffer.get("persons");
		 * 
		 * readPersons.forEach(a -> { MedicalRecords medicalRecords =
		 * medicalRecordsRepository.findByFirstNameAndLastName(a.get("firstName").
		 * toString(), a.get("lastName").toString()); persons.add(new
		 * Person(a.get("firstName").toString(), a.get("lastName").toString(),
		 * a.get("phone").toString(), a.get("email").toString(),
		 * a.get("address").toString(), a.get("city").toString(),
		 * a.get("zip").toString(), medicalRecords)); } );
		 */
		return persons;
	}
	/*
	 * public List<MedicalRecord> getMedicalRecords() { List<MedicalRecord>
	 * medicalRecords = new ArrayList<>(); Any medicalAny =
	 * buffer.get("medicalrecords"); System.out.println(buffer);
	 * /*medicalAny.forEach(b -> medicalRecords.add(new
	 * MedicalRecord.MedicalRecordBuilder().firstName(b.get("firstName").toString())
	 * .lastName(b.get("lastName").toString()).birthdate(b.get("birthdate").toString
	 * ()) ) );
	 */
	/*
	 * medicalAny.forEach(medicalRecord -> { //System.out //
	 * .println(medicalRecord.get("firstName").toString().concat(medicalRecord.get(
	 * "lastName").toString()) //
	 * .concat(medicalRecord.get("birthdate").toString())); Any medications =
	 * medicalRecord.get("medications"); medications.forEach(a ->
	 * System.out.println(a.toString())); Any allergies =
	 * medicalRecord.get("allergies"); allergies.forEach(a ->
	 * System.out.println(a.toString()));
	 * 
	 * });
	 * 
	 * return medicalRecords; }
	 */

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
		/*
		 * 				.add(new MedicalRecord(a.get("firstName").toString(), a.get("lastName").toString(),
						a.get("birthdate").toString(), getList(a.get("medications")), getList(a.get("allergies")))));
		 */

		//System.out.println(buffer);
		return medicalRecordsList;
	}

	/*
	 * Any personAny = any.get("persons"); System.out.println(personAny);
	 * List<Person> persons = new ArrayList<>(); personAny.forEach(a ->
	 * persons.add(new
	 * Person.PersonBuilder().firstName(a.get("firstName").toString())
	 * .address(a.get("address").toString()).city(a.get("city").toString())
	 * .lastName(a.get("lastName").toString()).phone(a.get("phone").toString()).zip(
	 * a.get("zip").toString()) .email(a.get("email").toString()).build()));
	 * persons.forEach(p -> System.out.println(
	 * p.firstName.concat(p.lastName).concat(p.address).concat(p.city).concat(p.
	 * phone).concat(p.zip)));
	 * 
	 * }
	 */

}

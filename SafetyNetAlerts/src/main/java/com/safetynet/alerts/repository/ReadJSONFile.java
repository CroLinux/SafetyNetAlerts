package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.alerts.model.Person;

@Repository
public class ReadJSONFile {
	
	private static Any buffer;

	//public static void main(String[] args) throws IOException {
	public ReadJSONFile () throws IOException {
		String filePath = "src/main/resources/data.json";
		byte[] bytesFile = Files.readAllBytes(new File(filePath).toPath());
		JsonIterator jsonIterator = JsonIterator.parse(bytesFile);
		buffer = jsonIterator.readAny();
	}
	
    public List<Person> getPersons() throws IOException {
        List<Person> persons = new ArrayList<>();
        Any personAny = buffer.get("persons");
		personAny.forEach(a -> persons.add(new Person.PersonBuilder().firstName(a.get("firstName").toString())
				.address(a.get("address").toString()).city(a.get("city").toString())
				.lastName(a.get("lastName").toString()).phone(a.get("phone").toString()).zip(a.get("zip").toString())
				.email(a.get("email").toString()).build()));
		persons.forEach(p -> System.out.println(
				p.firstName.concat(p.lastName).concat(p.address).concat(p.city).concat(p.phone).concat(p.zip)));
        
        /*
        MedicalRecordsRepository medicalRecordsRepository = new MedicalRecordsRepository();

        Any readPersons = buffer.get("persons");

        readPersons.forEach(a -> {
            MedicalRecords medicalRecords = medicalRecordsRepository.findByFirstNameAndLastName(a.get("firstName").toString(), a.get("lastName").toString());
            persons.add(new Person(a.get("firstName").toString(), a.get("lastName").toString(),
                                a.get("phone").toString(), a.get("email").toString(), a.get("address").toString(),
                                a.get("city").toString(), a.get("zip").toString(), medicalRecords));
        }
        );*/
        return persons;
    }
    
	/*
		Any personAny = any.get("persons");
		System.out.println(personAny);
		List<Person> persons = new ArrayList<>();
		personAny.forEach(a -> persons.add(new Person.PersonBuilder().firstName(a.get("firstName").toString())
				.address(a.get("address").toString()).city(a.get("city").toString())
				.lastName(a.get("lastName").toString()).phone(a.get("phone").toString()).zip(a.get("zip").toString())
				.email(a.get("email").toString()).build()));
		persons.forEach(p -> System.out.println(
				p.firstName.concat(p.lastName).concat(p.address).concat(p.city).concat(p.phone).concat(p.zip)));

	}
	*/
	

}

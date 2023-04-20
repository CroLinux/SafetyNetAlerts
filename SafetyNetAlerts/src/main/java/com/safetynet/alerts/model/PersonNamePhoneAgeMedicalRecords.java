package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class PersonNamePhoneAgeMedicalRecords {

	private String firstName;
	private String lastName;
	private String phone;
	private String age;
	private List<String> medications;
	private List<String> allergies;

	// Default Constructor
	public PersonNamePhoneAgeMedicalRecords() {

	}

	// Generate COnstructor
	public PersonNamePhoneAgeMedicalRecords(String firstName, String lastName, String phone, String age,
			List<String> medications, List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}

}

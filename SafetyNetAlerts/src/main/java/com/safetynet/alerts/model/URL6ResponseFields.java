package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class URL6ResponseFields {

	private String firstName;
	private String lastName;
	private String address;
	private String age;
	private String email;
	private List<String> medication;
	private List<String> allergies;

	public URL6ResponseFields() {
		
	}

	public URL6ResponseFields(String firstName, String lastName, String address, String age, String email,
			List<String> medication, List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medication = medication;
		this.allergies = allergies;
	}
	
	
}

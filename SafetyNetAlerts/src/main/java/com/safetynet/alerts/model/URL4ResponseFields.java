package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class URL4ResponseFields {

	// Definition of the fields
	public int station;
	public String firstName;
	public String lastName;
	public String phone;
	public String age;
	public List<String> medications;
	public List<String> allergies;

	public URL4ResponseFields() {

	}

	public URL4ResponseFields(int station, String firstName, String lastName, String phone, String age,
			List<String> medications, List<String> allergies) {
		super();
		this.station = station;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}

}
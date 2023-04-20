package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class PersonFullData {
	
	// Definition of the fields
	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String zip;
	public String phone;
	public String email;
	public String birthdate;
	public String age;
	public List<String> medications;
	public List<String> allergies;
	public int station;
	
	// Default Constructor
	public PersonFullData() {
		
	}
	
	// Generate Constructor
	public PersonFullData(String firstName, String lastName, String address, String city, String zip, String phone,
			String email, String birthdate, String age, List<String> medications, List<String> allergies, int station) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.birthdate = birthdate;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
		this.station = station;
	}	
	
	
	
}

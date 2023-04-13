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
	
}

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

}
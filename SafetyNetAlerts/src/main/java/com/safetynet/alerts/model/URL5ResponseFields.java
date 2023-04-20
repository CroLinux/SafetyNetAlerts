package com.safetynet.alerts.model;

import java.util.List;
import java.util.ArrayList;

import lombok.Data;

@Data
public class URL5ResponseFields {

	// Definition of the fields
	private String address;

	private List<PersonNamePhoneAgeMedicalRecords> residents;

	// Default Constructor
	public URL5ResponseFields() {

	}

	// Generate Constructor
	public URL5ResponseFields(String address, List<PersonNamePhoneAgeMedicalRecords> residents) {
		super();
		this.address = address;
		this.residents = residents;
	}

	// Allow to set one resident or a list of resident
	public void setResidents(PersonNamePhoneAgeMedicalRecords resident) {
		if (this.residents == null) {
			this.residents = new ArrayList<>();
		}
		this.residents.add(resident);
	}

	public void setResidents(List<PersonNamePhoneAgeMedicalRecords> residents) {
		this.residents = residents;
	}

}

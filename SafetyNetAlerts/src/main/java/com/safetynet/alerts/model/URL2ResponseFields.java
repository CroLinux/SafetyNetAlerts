package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class URL2ResponseFields {

	private String firstName;
	private String lastName;
	private String age;
	private List<String> withResidents;

	public URL2ResponseFields() {
	}

	public URL2ResponseFields(String firstName, String lastName, String age, List<String> withResidents) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.withResidents = withResidents;
	}

}

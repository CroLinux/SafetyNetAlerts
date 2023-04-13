package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class URL2ResponseFields {
	
	private String firstName;
	private String lastName;
	private String age;
	private List<String> withResidents;

}

package com.safetynet.alerts.model;

import lombok.Data;

@Data
public class URL1ResponseFields {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private int adults;
	private int kids;

	public URL1ResponseFields() {

	}

	public URL1ResponseFields(String firstName, String lastName, String address, String phone, int adults, int kids) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.adults = adults;
		this.kids = kids;
	}

}

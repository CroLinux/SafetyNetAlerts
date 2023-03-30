package com.safetynet.alerts.model;

import lombok.Data;

@Data
public class Person {

	// Definition of the fields
	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String zip;
	public String phone;
	public String email;

	// Generate the Consturctor
	public Person(String firstName, String lastName, String address, String city, String zip, String phone,
			String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	public static class PersonBuilder {
		private String firstName;
		private String lastName;
		private String phone;
		private String zip;
		private String address;
		private String city;
		private String email;

		public PersonBuilder() {
		}

		public PersonBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public PersonBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public PersonBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public PersonBuilder zip(String zip) {
			this.zip = zip;
			return this;
		}

		public PersonBuilder address(String address) {
			this.address = address;
			return this;
		}

		public PersonBuilder city(String city) {
			this.city = city;
			return this;
		}

		public PersonBuilder email(String email) {
			this.email = email;
			return this;
		}

		public Person build() {
			return new Person(firstName, lastName, phone, zip, address, city, email);
		}
	}
}

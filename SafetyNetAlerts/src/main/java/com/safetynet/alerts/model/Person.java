package com.safetynet.alerts.model;

import com.jsoniter.annotation.JsonProperty;

import lombok.Data;

@Data
public class Person {

	// Definition of the fields
	@JsonProperty("firstName")
	public String firstName;
	@JsonProperty("lastName")
	public String lastName;
	@JsonProperty("address")
	public String address;
	@JsonProperty("city")
	public String city;
	@JsonProperty("zip")
	public String zip;
	@JsonProperty("phone")
	public String phone;
	@JsonProperty("email")
	public String email;

	// Default Constructor
	public Person () {
		
	}
	
	// Generate the Constructor
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
		private String address;
		private String city;
		private String zip;
		private String phone;
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

		public PersonBuilder address(String address) {
			this.address = address;
			return this;
		}

		public PersonBuilder city(String city) {
			this.city = city;
			return this;
		}

		public PersonBuilder zip(String zip) {
			this.zip = zip;
			return this;
		}

		public PersonBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public PersonBuilder email(String email) {
			this.email = email;
			return this;
		}

		public Person build() {
			return new Person(firstName, lastName, address, city, zip, phone, email);
		}
	}
	
	
}

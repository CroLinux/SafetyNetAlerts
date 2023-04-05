package com.safetynet.alerts.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MedicalRecord {

	// Definition of the fields
	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	
	// Generate Constructor
	public MedicalRecord(String firstName, String lastName, String birthdate, List<String> medications,
			List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	/*
	public static class MedicalRecordBuilder {
		private String firstName;
		private String lastName;
		private String birthdate;
		private List<String> medications;
		private List<String> allergies;


		public MedicalRecordBuilder() {
		}

		public MedicalRecordBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public MedicalRecordBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public MedicalRecordBuilder birthdate(String birthdate) {
			this.birthdate = birthdate;
			return this;
		}

		public MedicalRecordBuilder medications(List<String> medications) {
			this.medications = medications;
			return this;
		}

		public MedicalRecordBuilder allergies(List<String> allergies) {
			this.allergies = allergies;
			return this;
		}


		public MedicalRecord build() {
			return new MedicalRecord(firstName, lastName, birthdate, medications, allergies);
		}
	}
*/
}

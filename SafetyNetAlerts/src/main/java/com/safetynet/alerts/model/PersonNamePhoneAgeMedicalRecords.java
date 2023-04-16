package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class PersonNamePhoneAgeMedicalRecords {
	
    private String firstName;

    private String lastName;

    private String phone;

    private String age;

    private List<String> medications;

    private List<String> allergies;

}

package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class URL6ResponseFields {
	
    private String firstName;
    private String lastName;
    private String address;
    private String age;
    private String email;
    private List<String> medication;
    private List<String> allergies;

}

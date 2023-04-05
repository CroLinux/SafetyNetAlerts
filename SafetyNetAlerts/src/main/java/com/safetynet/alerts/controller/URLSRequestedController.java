package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.URL6ResponseFields;
import com.safetynet.alerts.service.PersonService;

@RestController
public class URLSRequestedController {

	@Autowired
	private PersonService personService;

	/*
	 * URL 6 -
	 * http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>.
	 * We should get here: Name - Address - Age - Email - MedicalRecords. We have to
	 * provide here: FirstName and LastName
	 */
	@GetMapping("/personInfo")
	public List<URL6ResponseFields> getPersonInfo3(@RequestParam String firstName, @RequestParam String lastName)
			throws IOException {

		List<URL6ResponseFields> resultURL6 = personService.findPersonAndMedicalRecordsByFirstNameAndLastName(firstName,
				lastName);

		return resultURL6;
	}

}

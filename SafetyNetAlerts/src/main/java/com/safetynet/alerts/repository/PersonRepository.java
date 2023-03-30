package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepository {
	
    private static List<Person> personList = new ArrayList<>();

    public PersonRepository() throws IOException {
         ReadJSONFile readJSONFile = new ReadJSONFile();
         personList = readJSONFile.getPersons();
    }

    public List<Person> getPersons() {
         return personList;
    }

}

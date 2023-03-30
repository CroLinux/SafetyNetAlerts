/*
package com.safetynet.alerts.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.any.Any;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.ReadJSONFile;

	
	@RestController
	@RequestMapping("/")
	public class PersonController {
		/*
		ReadJSONFile readerFile = new ReadJSONFile();
		Any personsData = readerFile.getPersons();
		
	
	    private final List<Person> persons = new ArrayList<>();
	    //private final List<MedicalRecord> medicalRecords = new ArrayList<>();
	    
	    
	    
	    

	    @GetMapping("/personInfo")
	    public List<Person> getPersons(@RequestParam("firstName") String firstName,
	                                   @RequestParam("lastName") String lastName) {
	        return persons.stream()
	                .filter(person -> person.getFirstname().equals(firstName) && person.getLastname().equals(lastName))
	                .collect(Collectors.toList());
	    	
	    }
	    
		
	
		
	    @Autowired
	    private ReadJSONFile readJSONFile;
	    
	    @GetMapping("/personInfo")
	    public Map<String, Object> getPersonInfo(@RequestParam("firstName") String firstName,
	                                             @RequestParam("lastName") String lastName) {
	    	List<Person> Tab = new ArrayList<Person>();
	        Any persons2 = readJSONFile.getPersons(Tab);
	        Any medicalRecords = readJSONFile.getMedicalrecords();

	        System.out.println("Début");
	        
	        Map<String, Object> result = new HashMap<>();

	        for (Any person : persons2) {
	        	System.out.println("début 2");
	        	
	            String personFirstName = person.get("firstName").toString();
	            String personLastName = person.get("lastName").toString();
	            String personAddress = person.get("address").toString();
	            String personEmail = person.get("email").toString();

	            if (personFirstName.equalsIgnoreCase(firstName) && personLastName.equalsIgnoreCase(lastName)) {
	                result.put("firstName", personFirstName);
	                result.put("lastName", personLastName);
	                result.put("address", personAddress);
	                result.put("email", personEmail);

	                LocalDate birthdate = LocalDate.parse(medicalRecords.get(personFirstName + "." + personLastName + ".birthdate").toString());
	                Period age = Period.between(birthdate, LocalDate.now());
	                result.put("age", age.getYears());

	                Any medications = medicalRecords.get(personFirstName + "." + personLastName + ".medications");
	                Any allergies = medicalRecords.get(personFirstName + "." + personLastName + ".allergies");

	                result.put("medications", medications);
	                result.put("allergies", allergies);
	                
	            	System.out.println("fin");

	                break;
	            }
	        }

	        return result;
	    }
		
		
	    @GetMapping("/msg")
	    public String getMsg() {
	    	String message = "Hello MOTO";
	        return message;
	    }
	    /*
	    @GetMapping("/tab")
	    public Any getData() {
			return personsData;
	    	
	    }
	    
	    
/*
	    @PostMapping("/person")
	    public void addPerson(@RequestBody Person person) {
	        persons.add(person);
	    }

	    @PutMapping("/person/{id}")
	    public void updatePerson(@PathVariable("id") int id, @RequestBody Person person) {
	        persons.set(id, person);
	    }

	    @DeleteMapping("/person/{id}")
	    public void deletePerson(@PathVariable("id") int id) {
	        persons.remove(id);
	    }

	}
*/

package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.ReadJSONFile;

@RestController
public class PersonController {

	@Autowired
	private ReadJSONFile readJSONFile;
/*
	@GetMapping("/personInfo")
	public List<Person> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) {
		List<Person> persons = null;
		try {
			persons = readJSONFile.getPersons();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Person> result = new ArrayList<>();

		for (Person person : persons) {
			if (person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
				result.add(person);
			}
		}

		return result;
	}
	*/
	
	@GetMapping("/personInfo")
	public List<Person> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) throws IOException {
		List<Person> persons = readJSONFile.getPersons();
		List<Person> result = new ArrayList<>();
		for (Person person : persons) {
			if (person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
				result.add(person);
			}
		}

		return result;
	}
	
    @GetMapping("/person")
    public List<Person> personsList() throws IOException {
        List<Person> personList = readJSONFile.getPersons();
        return personList;
    }
}

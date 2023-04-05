package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {

	private static List<MedicalRecord> medicalRecordList = new ArrayList<>();

	public MedicalRecordRepository() throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		medicalRecordList = readJSONFile.getMedicalRecords();
	}
	
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordList;
   }

	public Optional<MedicalRecord> getPersonByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}

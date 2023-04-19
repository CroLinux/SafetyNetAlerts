package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {
	
    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) throws IOException {
    	List<MedicalRecord> getMedicalRecordResult = MedicalRecordService.getMedicalRecordInDataSource(firstName, lastName);
        return getMedicalRecordResult;
    }
    
    @PostMapping("/medicalRecord")
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
    	MedicalRecordService.addMedicalRecordInDataSource(medicalRecord);
        return medicalRecord;
    }

    @PutMapping("/medicalRecord")
    public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
    	MedicalRecordService.updateMedicalRecordInDataSource(medicalRecord);
        return medicalRecord;
    }
    
    @DeleteMapping("/medicalRecord")
    public MedicalRecord deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) throws IOException {
    	MedicalRecordService.deleteMedicalRecordInDataSource(medicalRecord);
        return medicalRecord;
    }

}

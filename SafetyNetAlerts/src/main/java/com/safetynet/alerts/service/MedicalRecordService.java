package com.safetynet.alerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

	@Autowired
	private static MedicalRecordRepository medicalRecordRepository;

	public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
		MedicalRecordService.medicalRecordRepository = medicalRecordRepository;
	}

	/**
	 * 
	 * --- ENDPOINTS FOR MedicalRecord ---
	 * 
	 * @return
	 * @throws IOException
	 * 
	 */
	
	public static List<MedicalRecord> getMedicalRecordInDataSource(String firstName, String lastName)
			throws IOException {
		List<MedicalRecord> getMedicalRecordList = medicalRecordRepository.getMedicalRecordInDataSource(firstName,
				lastName);
		return getMedicalRecordList;
	}

	public static List<MedicalRecord> addMedicalRecordInDataSource(MedicalRecord medicalRecord) throws IOException {
		List<MedicalRecord> addMedicalRecordList = medicalRecordRepository.addMedicalRecordInDataSource(medicalRecord);
		return addMedicalRecordList;
	}

	public static List<MedicalRecord> updateMedicalRecordInDataSource(MedicalRecord medicalRecord) throws IOException {
		List<MedicalRecord> updateMedicalRecordList = medicalRecordRepository
				.updateMedicalRecordInDataSource(medicalRecord);
		return updateMedicalRecordList;
	}

	public static List<MedicalRecord> deleteMedicalRecordInDataSource(MedicalRecord medicalRecord) throws IOException {
		List<MedicalRecord> deleteMedicalRecordList = medicalRecordRepository
				.deleteMedicalRecordInDataSource(medicalRecord);
		return deleteMedicalRecordList;
	}

}

package com.safetynet.alerts.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;

@Service
public class FirestationService {

	@Autowired
	private static FirestationRepository firestationRepository;

	public FirestationService(FirestationRepository firestationRepository) {
		FirestationService.firestationRepository = firestationRepository;
	}

	/**
	 * 
	 * --- ENDPOINTS FOR Firestation ---
	 * 
	 * @return
	 * @throws IOException
	 * 
	 */

	public static List<Firestation> getFirestationInDataSource(int stationNumber) throws IOException {
		List<Firestation> getFirestationList = firestationRepository.getFirestationInDataSource(stationNumber);
		return getFirestationList;
	}

	public static List<Firestation> addFirestationInDataSource(Firestation firestation) throws IOException {
		List<Firestation> addfirestationList = firestationRepository.addFirestationInDataSource(firestation);
		return addfirestationList;
	}

	public static List<Firestation> updateFirestationInDataSource(Firestation firestation) throws IOException {
		List<Firestation> updatefirestationList = firestationRepository.updateFirestationInDataSource(firestation);
		return updatefirestationList;
	}

	public static List<Firestation> deleteFirestationInDataSource(Firestation firestation) throws IOException {
		List<Firestation> deletefirestationList = firestationRepository.deleteFirestationInDataSource(firestation);
		return deletefirestationList;
	}

}

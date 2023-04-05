package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestation;

@Repository
public class FirestationRepository {

	private static List<Firestation> firestationList = new ArrayList<>();

	public FirestationRepository() throws IOException {
		ReadJSONFile readJSONFile = new ReadJSONFile();
		firestationList = readJSONFile.getFirestations();

	}

	public List<Firestation> getFirestations() {
		return firestationList;
	}

	public List<Firestation> getFirestationsByID(int stationNumber) {
		List<Firestation> firestationListByID = new ArrayList<>();
		for (Firestation firestation : firestationList) {
			if (firestation.getStation() == stationNumber) {
				firestationListByID.add(firestation);
			}

		}
		return firestationListByID;
	}

}

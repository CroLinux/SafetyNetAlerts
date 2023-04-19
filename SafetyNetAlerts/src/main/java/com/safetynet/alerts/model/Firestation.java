package com.safetynet.alerts.model;

import lombok.Data;

@Data
public class Firestation {

	String address;
	int station;
	
	public Firestation() {
		
	}

	// Generate the Constructor
	public Firestation(String address, int station) {
		super();
		this.address = address;
		this.station = station;
	}

}

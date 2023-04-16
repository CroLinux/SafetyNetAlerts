package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is used to write the requested output file in json format.
 * We get here different kind of List types.
 *
 * @author CroLinux
 *
 */

@Repository
public class WriteJSONFile {

	public void writeIntoTheFile(List<?> dataToWriteIntoTheFile) {

		String outputFilePath = "src/main/resources/dataOutput.json";

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writeValue(new File(outputFilePath), dataToWriteIntoTheFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

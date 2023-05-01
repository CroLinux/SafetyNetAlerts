package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is used to write the requested output from the provided URLs in a Json file (format).
 * We get here different kind of List types.
 *
 * @author CroLinux
 *
 */

@Repository
public class WriteJSONOutputFile {
	
	private Logger logger = LogManager.getLogger(WriteJSONOutputFile.class);

	public void writeIntoTheFile(List<?> dataToWriteIntoTheFile) {

		String outputFilePath = "src/main/resources/dataOutput.json";

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writeValue(new File(outputFilePath), dataToWriteIntoTheFile);
		} catch (IOException e) {
			logger.error("Error with the Output JSON File.", e);
		}

	}

}

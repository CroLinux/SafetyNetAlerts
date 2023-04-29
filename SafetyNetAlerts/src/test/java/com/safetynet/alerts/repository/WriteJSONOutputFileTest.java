package com.safetynet.alerts.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WriteJSONOutputFileTest {

	private WriteJSONOutputFile writeJSONOutputFile;
	private List<String> dataToWrite;

	@Test
	public void testWriteIntoTheFile() {
		// Given
		writeJSONOutputFile = new WriteJSONOutputFile();
		dataToWrite = new ArrayList<>();
		dataToWrite.add("Person 1");
		dataToWrite.add("Person 2");
		// When
		writeJSONOutputFile.writeIntoTheFile(dataToWrite);
		File outputFile = new File("src/main/resources/dataOutput.json");
		// Then
		assertTrue(outputFile.exists());
	}
	
	@Test
	public void testWriteIntoTheFileNotPresent() {
		// Given
		writeJSONOutputFile = new WriteJSONOutputFile();
		dataToWrite = new ArrayList<>();
		dataToWrite.add("Person 1");
		dataToWrite.add("Person 2");
		// When
		writeJSONOutputFile.writeIntoTheFile(dataToWrite);
		File outputFile = new File("src/main/resources/dataOutput2.json");
		// Then
		assertFalse(outputFile.exists());
	}

}

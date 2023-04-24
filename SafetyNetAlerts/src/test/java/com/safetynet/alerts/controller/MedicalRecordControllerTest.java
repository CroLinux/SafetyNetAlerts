package com.safetynet.alerts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.service.MedicalRecordService;

@SpringBootTest
public class MedicalRecordControllerTest {

    private MockMvc mockMvc;
    private MedicalRecordService medicalRecordService;
    private MedicalRecordRepository medicalRecordRepository;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
    	medicalRecordRepository = Mockito.mock(MedicalRecordRepository.class);
    	medicalRecordService = new MedicalRecordService(medicalRecordRepository);
        medicalRecordService = Mockito.mock(MedicalRecordService.class);
        MedicalRecordController medicalRecordController = new MedicalRecordController();
        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetMedicalRecord() throws Exception {
        // Given
        String firstName = "Person";
        String lastName = "Test";
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(firstName);
        medicalRecord.setLastName(lastName);
        medicalRecordList.add(medicalRecord);
        when(medicalRecordService.getMedicalRecordInDataSource(firstName, lastName)).thenReturn(medicalRecordList);

        // When
        mockMvc.perform(get("/medicalRecord")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(firstName))
                .andExpect(jsonPath("$[0].lastName").value(lastName));
    }

    @Test
    public void testCreateMedicalRecord() throws Exception {
        // Given
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Person");
        medicalRecord.setLastName("Test");
        String json = objectMapper.writeValueAsString(medicalRecord);

        // When
        mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(medicalRecord.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(medicalRecord.getLastName()));
    }

    @Test
    public void testUpdateMedicalRecord() throws Exception {
        // Given
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Person");
        medicalRecord.setLastName("Test");
        String json = objectMapper.writeValueAsString(medicalRecord);

        // When
        mockMvc.perform(put("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(medicalRecord.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(medicalRecord.getLastName()));
    }

    @Test
    public void testDeleteMedicalRecord() throws Exception {
        // Given
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Person");
        medicalRecord.setLastName("Test");
        String json = objectMapper.writeValueAsString(medicalRecord);

        // When
        mockMvc.perform(delete("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(medicalRecord.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(medicalRecord.getLastName()));
    }
}


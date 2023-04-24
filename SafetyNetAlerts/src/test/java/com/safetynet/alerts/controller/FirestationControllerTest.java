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
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.service.FirestationService;

@SpringBootTest
public class FirestationControllerTest {

    private MockMvc mockMvc;
    private FirestationService firestationService;
    private FirestationRepository firestationRepository;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
    	firestationRepository = Mockito.mock(FirestationRepository.class);
    	firestationService = new FirestationService(firestationRepository);
        firestationService = Mockito.mock(FirestationService.class);
        FirestationController firestationController = new FirestationController();
        mockMvc = MockMvcBuilders.standaloneSetup(firestationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetFirestation() throws Exception {
        // Given
        int stationNumber = 1;
        List<Firestation> firestationList = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setStation(stationNumber);
        firestationList.add(firestation);
        when(firestationService.getFirestationInDataSource(stationNumber)).thenReturn(firestationList);

        // When
        mockMvc.perform(get("/firestationOnly")
                .param("stationNumber", String.valueOf(stationNumber))
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].station").value(stationNumber));
    }

    @Test
    public void testCreateFirestation() throws Exception {
        // Given
        Firestation firestation = new Firestation();
        firestation.setStation(1);
        String json = objectMapper.writeValueAsString(firestation);

        // When
        mockMvc.perform(post("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value(firestation.getStation()));
    }

    @Test
    public void testUpdateFirestation() throws Exception {
        // Given
        Firestation firestation = new Firestation();
        firestation.setStation(1);
        String json = objectMapper.writeValueAsString(firestation);

        // When
        mockMvc.perform(put("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value(firestation.getStation()));
    }

    @Test
    public void testDeleteFirestation() throws Exception {
        // Given
        Firestation firestation = new Firestation();
        firestation.setStation(1);
        String json = objectMapper.writeValueAsString(firestation);

        // When
        mockMvc.perform(delete("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))

        // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value(firestation.getStation()));
    }

}



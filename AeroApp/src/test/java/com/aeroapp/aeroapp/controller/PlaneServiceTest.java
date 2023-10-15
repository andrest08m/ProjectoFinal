package com.aeroapp.aeroapp.controller;

import com.aeroapp.aeroapp.Service.FlightsServiceImp;
import com.aeroapp.aeroapp.Service.PlaneServiceImp;
import com.aeroapp.aeroapp.dto.PlaneDTO;
import com.aeroapp.aeroapp.dto.ReservationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureJson
@WebAppConfiguration
public class PlaneServiceTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private PlaneServiceImp planeServiceImp;

    @Test
    void createBooking() throws Exception {
        // Supongamos que tienes un objeto reservationDTO válido para la creación
        PlaneDTO planeDTO = new PlaneDTO();
        // Configura el comportamiento esperado del servicio
        Mockito.when(planeServiceImp.createPlane(planeDTO))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Reservation created."));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/plane")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(planeDTO))) // Convierte el objeto ReservationDTO a JSON
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

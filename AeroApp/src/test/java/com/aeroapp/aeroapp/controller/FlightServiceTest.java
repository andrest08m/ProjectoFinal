package com.aeroapp.aeroapp.controller;

import com.aeroapp.aeroapp.Entity.Plane;
import com.aeroapp.aeroapp.Entity.Reservation;
import com.aeroapp.aeroapp.Entity.Flight;
import com.aeroapp.aeroapp.Service.FlightsServiceImp;
import com.aeroapp.aeroapp.dto.FlightDTO;
import com.aeroapp.aeroapp.utils.Airline;
import com.aeroapp.aeroapp.utils.FlightType;
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
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJson
@WebAppConfiguration
public class FlightServiceTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private FlightsServiceImp flightsServiceImp;

    @Test
    void getAllFlights() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/flights")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithUserDetails(value = "Admin")
    void createFlights() throws Exception {
        // Supongamos que tienes un objeto flightDTO válido para la creación
        FlightDTO customerDTO = new FlightDTO();
        Airline airline = Airline.LATAM;
        Plane plane = new Plane();
        Set<Reservation> reservations = new HashSet<>();
        FlightType flightType = FlightType.PUBLIC;
        LocalDate localDate = LocalDate.parse("1991-11-21");
        Date date = Date.valueOf(localDate);

        customerDTO.setPlane_id("AV321");
        customerDTO.setPlane(plane);
        customerDTO.setAirline(airline);
        customerDTO.setAvailableSeats(130);
        customerDTO.setClientes(reservations);
        customerDTO.setCode("TEST_CODE");
        customerDTO.setDestiny("SKRG");
        customerDTO.setOrigin("SKBO");
        customerDTO.setPrice(120.0);
        customerDTO.setType(flightType);
        customerDTO.setArrivalDate(date);
        customerDTO.setDepartureDate(date);


        // Suponemos que el servicio crea el vuelo con éxito
        Mockito.when(flightsServiceImp.createVueloDTO(customerDTO))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(""));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/flights")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO))) // Convierte el objeto CustomerDTO a JSON
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    // Método para convertir un objeto Java a formato JSON
    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getFlightById() throws Exception {
        Long id = 1L;
        Flight customer = new Flight();

        Mockito.when(flightsServiceImp.findById(id)).thenReturn(ResponseEntity.status(200).body(customer));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/flights/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    void updateFlight() throws Exception {
        // Supongamos que tienes un objeto reservaDTO con los datos actualizados
        FlightDTO flightDTO = new FlightDTO();
        Long id = 1L; // ID de la reserva que deseas actualizar

        // Configura el comportamiento esperado del servicio para actualizar la reserva
        Mockito.when(flightsServiceImp.updateFlight(flightDTO, id))
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body("Reservation updated successfully."));

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/flights/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(flightDTO))) // Convierte el objeto ReservaDTO a JSON
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }


    @Test
    void deleteFlight() throws Exception {
        Long id = 1L;

        // Suponemos que el servicio eliminará el cliente con éxito
        Mockito.doReturn(null).when(flightsServiceImp).deleteFlight(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/flights/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}

package com.aeroapp.aeroapp.controller;

import com.aeroapp.aeroapp.Service.ReservationServiceImp;
import com.aeroapp.aeroapp.dto.ReservationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
@AutoConfigureMockMvc
@AutoConfigureJson
@WebAppConfiguration
public class ReservationServiceTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ReservationServiceImp reservationServiceImp;

    @BeforeEach
    public void setUp() {
        // Configura comportamiento esperado para el servicio en cada prueba si es necesario
    }

    @Test
    void getAllBookings() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/reserva")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createBooking() throws Exception {
        // Supongamos que tienes un objeto reservationDTO válido para la creación
        ReservationDTO reservationDTO = new ReservationDTO();
        // Configura el comportamiento esperado del servicio
        Mockito.when(reservationServiceImp.createReserva(reservationDTO))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Reservation created."));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/reserva")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(reservationDTO))) // Convierte el objeto ReservationDTO a JSON
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void getBookingById() throws Exception {
        Long id = 1L;
        ReservationDTO reserva = new ReservationDTO();
        // Configura el comportamiento esperado del servicio
        Mockito.when(reservationServiceImp.findById(id)).thenReturn(reserva);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/reserva/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateBooking() throws Exception {
        // Supongamos que tienes un objeto reservaDTO con los datos actualizados
        ReservationDTO reservaDTO = new ReservationDTO();
        Long id = 1L; // ID de la reserva que deseas actualizar

        // Configura el comportamiento esperado del servicio para actualizar la reserva
        Mockito.when(reservationServiceImp.updateReserva(reservaDTO, id))
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body("Reservation updated successfully."));

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/reserva/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(reservaDTO))) // Convierte el objeto ReservaDTO a JSON
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }


    @Test
    void deleteFlight() throws Exception {
        Long id = 1L;

        // Suponemos que el servicio eliminará el cliente con éxito
        Mockito.doReturn(null).when(reservationServiceImp).deleteReserva(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/reserva/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
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
}

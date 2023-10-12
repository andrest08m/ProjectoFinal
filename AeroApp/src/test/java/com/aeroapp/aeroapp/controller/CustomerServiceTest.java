package com.aeroapp.aeroapp.controller;


import com.aeroapp.aeroapp.Entity.Customer;
import com.aeroapp.aeroapp.Entity.Plane;
import com.aeroapp.aeroapp.Entity.Reserva;
import com.aeroapp.aeroapp.Entity.Vuelo;
import com.aeroapp.aeroapp.Repository.CustomerRepository;
import com.aeroapp.aeroapp.Service.CustomerServicelmp;
import com.aeroapp.aeroapp.dto.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CustomerServicelmp customerServicelmp;

    @Test
    void getAllCustomers_success() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/customer")
                        .with(SecurityMockMvcRequestPostProcessors.user("Admin").password("Admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

//    @Test
//    void createClient() throws Exception {
//        CustomerDTO client = new CustomerDTO();
//
//        List<Customer> customerList = new ArrayList<>();
//
//        java.sql.Date date = java.sql.Date.valueOf("1999-08-06");
//
//        client.setName("Daniel");
//        client.setLast_name("Espinosa");
//        client.setCustomer_reservation(1);
//        client.setReservation_number("ASDASDASD");
//        client.setReservation_number("SADASD");
//        client.setGender("M");
//        LocalDateTime localDateTime =
//                LocalDateTime.of(2023, 10, 11, 15, 30, 0);
//        Vuelo plane = new Vuelo();
//
//        Reserva reserva = new Reserva(1L,
//                localDateTime, localDateTime,"Suite","1"
//        , (byte) 1, "1", plane,customerList );
//
//        Mockito.when(customerServicelmp.createCustomerDTO(Mockito.any(CustomerDTO.class))).
//                thenReturn(ResponseEntity.status(201).body("Created"));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/v1/customer")
//                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Admin", "Admin"))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(client)))
//                .andExpect(MockMvcResultMatchers.status().is(201));
//    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCustomerById() throws Exception{
        int id = 1;
        CustomerDTO customer = new CustomerDTO();

        Mockito.when(customerServicelmp.getById(id)).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customer/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Admin", "Admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void deleteClient() throws Exception {
        int id = 1;
        Customer client = new Customer();
        client.setCustomer_id(id);

        Mockito.doNothing().when(customerServicelmp).deleteCustomer(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/clients/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Admin", "Admin")))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

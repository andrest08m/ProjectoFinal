package com.aeroapp.aeroapp.controller;



import com.aeroapp.aeroapp.Service.CustomerServicelmp;
import com.aeroapp.aeroapp.dto.CustomerDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CustomerServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CustomerServicelmp customerServicelmp;

    @Test
    void getAllCustomers_success() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/customer")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }



    @Test
    @WithUserDetails(value = "Admin")
    void createCustomer() throws Exception {
        // Supongamos que tienes un objeto CustomerDTO válido para la creación
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John");
        customerDTO.setLast_name("Doe");
        customerDTO.setCell_phone(Long.parseLong("1234567890"));
        customerDTO.setGender("Male");
        customerDTO.setCustomer_reservation(123);

        // Suponemos que el servicio crea el cliente con éxito
        Mockito.when(customerServicelmp.createCustomerDTO(customerDTO))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body("Customer created"));

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/customer")
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
    void getCustomerById() throws Exception{
        int id = 1;
        CustomerDTO customer = new CustomerDTO();

        Mockito.when(customerServicelmp.getById(id)).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customer/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    void deleteCustomerById() throws Exception {
        int id = 1;

        // Suponemos que el servicio eliminará el cliente con éxito
        Mockito.doReturn(null).when(customerServicelmp).deleteCustomer(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/customer/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}

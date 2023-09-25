package com.aeroapp.aeroapp.Service;

import com.aeroapp.aeroapp.Entity.Customer;

import com.aeroapp.aeroapp.Entity.Reserva;
import com.aeroapp.aeroapp.Repository.CustomerRepository;
import com.aeroapp.aeroapp.Repository.ReservaRepository;
import com.aeroapp.aeroapp.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class CustomerServicelmp implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ReservaRepository reservaRepository;

    public List<Customer> getAllBookings() {
        return customerRepository.findAll();
    }


    public ResponseEntity<?> createCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = mapFromDTO(customerDTO);



        customerRepository.save(customer);

        return ResponseEntity.status(201).body(customer);
    }

    public CustomerDTO mapDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setName(customer.getName());
        customerDTO.setLast_name(customer.getLast_name());
        customerDTO.setCell_phone(customer.getCell_phone());
        customerDTO.setGender(customer.getGender());
        customerDTO.setCustomer_reservation(customer.getCustomer_reservation());

        return customerDTO;
    }


    public Customer mapFromDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setName(customerDTO.getName());
        customer.setLast_name(customerDTO.getLast_name());
        customer.setCell_phone(customerDTO.getCell_phone());
        customer.setGender(customerDTO.getGender());
        customer.setCustomer_reservation(customerDTO.getCustomer_reservation());

        return customer;
    }
}





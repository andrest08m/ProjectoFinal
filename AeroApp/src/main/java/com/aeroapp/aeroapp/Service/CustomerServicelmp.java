package com.aeroapp.aeroapp.Service;

import com.aeroapp.aeroapp.Entity.Customer;

import com.aeroapp.aeroapp.Entity.Reservation;
import com.aeroapp.aeroapp.Repository.CustomerRepository;
import com.aeroapp.aeroapp.Repository.ReservaRepository;
import com.aeroapp.aeroapp.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServicelmp {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ReservaRepository reservaRepository;

    public List<Customer> getAllBookings() {
        return customerRepository.findAll();
    }


    public ResponseEntity<String> createCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = mapFromDTO(customerDTO);
        List<Reservation> reservasList = reservaRepository.findAll();

        for(Reservation reservation : reservasList){
            if(reservation.getReservation_number() == customer.getCustomer_reservation()){
                customer.setReservation_number(reservation.getReservation_id());
            }
        }


        customerRepository.save(customer);

        return ResponseEntity.status(201).body("Customer created");
    }

    public CustomerDTO getById(int id){

        Optional<Customer> customer= customerRepository.findById(id);

        return customer.map(this::mapDTO).orElse(null);
    }

    public ResponseEntity<CustomerDTO> deleteCustomer(int id){
        Optional<Customer> custom = this.customerRepository.findById(id);
        this.customerRepository.delete(custom.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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





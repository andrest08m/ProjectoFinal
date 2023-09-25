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
    private CustomerRepository customerRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Customer> getAllBookings() {
        return customerRepository.findAll();
    }

    public String createReservationId(){
        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 10;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        return sb.toString();
    }
    public ResponseEntity<?> createcustomerDTO(CustomerDTO customerDTO) {
        Customer customer = mapFromDTO(customerDTO);
        ArrayList<Reserva> reservas = new ArrayList<>(reservaRepository.findAll());

        for (Reserva reserva : reservas) {
            if (reserva.getReservation_id().equals(customer.getCustomer_reservation())) {
                Optional<Reserva> reservaOptional = reservaRepository.findById((long) reserva.getReservaDisponible());
                if (reservaOptional.isPresent()) {
                    Reserva reservaEncontrada = reservaOptional.get();
                    customer.setReserva(reservaEncontrada);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reserva not found.");
                }
            }
        }
        return ResponseEntity.ok(customer);
    }




    public CustomerDTO createCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = mapFromDTO(customerDTO);
        customer.getReserva().setReservation_id(createReservationId());
        Customer newCustomer = customerRepository.save(customer);

        return mapDTO(newCustomer);
    }

    public CustomerDTO mapDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId_customer(customer.getId_customer());
        customerDTO.setName(customer.getName());
        customerDTO.setLast_name(customer.getLast_name());
        customerDTO.setCell_phone(customer.getCell_phone());
        customerDTO.setGender(customer.getGender());

        return customerDTO;
    }


    public Customer mapFromDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId_customer(customer.getId_customer());
        customer.setName(customer.getName());
        customer.setLast_name(customer.getLast_name());
        customer.setCell_phone(customer.getCell_phone());
        customer.setGender(customer.getGender());

        return customer;
    }
}





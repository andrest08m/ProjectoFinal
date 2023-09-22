package com.aeroapp.aeroapp.Service;

import com.aeroapp.aeroapp.Entity.Customer;

import com.aeroapp.aeroapp.Repository.CustomerRepository;
import com.aeroapp.aeroapp.Repository.ReservaRepository;
import com.aeroapp.aeroapp.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CustomerServicelmp implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Customer> getAllBookings() {
        return customerRepository.findAll();
    }


    public CustomerDTO createCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = mapFromDTO(customerDTO);
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





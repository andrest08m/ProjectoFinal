package com.aeroapp.aeroapp.controller;

import com.aeroapp.aeroapp.Entity.Customer;
import com.aeroapp.aeroapp.Service.CustomerServicelmp;
import com.aeroapp.aeroapp.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private  final CustomerServicelmp customerServicelmp;

    public CustomerController(CustomerServicelmp customerServicelmp) {
        this.customerServicelmp = customerServicelmp;
    }


    @GetMapping("")
    public List<Customer> getAllBookings() {
        return this.customerServicelmp.getAllBookings();
    }

    @PostMapping()
    public ResponseEntity createFlight(@RequestBody CustomerDTO customer) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.customerServicelmp.createCustomerDTO(customer));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}



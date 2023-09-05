package com.aeroapp.aeroapp.controller;


import com.aeroapp.aeroapp.Entity.Reserva;
import com.aeroapp.aeroapp.Service.ReservaServiceImp;

import com.aeroapp.aeroapp.dto.ReservaDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reserva")
public class ReservaController {
        @Autowired
        ReservaServiceImp reservaServiceImp;


        @GetMapping("")
        public List<Reserva> getAllBookings() {
            return this.reservaServiceImp.getAllBookings();
        }

        @PostMapping()
        public ResponseEntity createFlight(@RequestBody ReservaDTO reserva) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(this.reservaServiceImp.createReservaDTO(reserva));
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

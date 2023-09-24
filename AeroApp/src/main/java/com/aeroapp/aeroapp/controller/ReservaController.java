package com.aeroapp.aeroapp.controller;


import com.aeroapp.aeroapp.Entity.Reserva;
import com.aeroapp.aeroapp.Repository.PlaneRepository;
import com.aeroapp.aeroapp.Service.CustomerServicelmp;
import com.aeroapp.aeroapp.Service.ReservaServiceImp;

import com.aeroapp.aeroapp.Service.VueloServiceImp;
import com.aeroapp.aeroapp.dto.ReservaDTO;


import com.aeroapp.aeroapp.dto.VueloDTO;
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

    @Autowired
    CustomerServicelmp customerServicelmp;

    @Autowired
    public ReservaController(ReservaServiceImp reservaServiceImp,    CustomerServicelmp customerServicelmp){
        this.reservaServiceImp = reservaServiceImp;
        this.customerServicelmp = customerServicelmp;
    }

    @GetMapping("")
    public List<Reserva> getAllBookings() {
        return this.reservaServiceImp.getAllBookings();
    }
    @GetMapping("{id}")
    public ReservaDTO getFlightById(@PathVariable(name = "id") Long id){
        return reservaServiceImp.findById(id);
    }

    @PostMapping()
    public ResponseEntity createFlight(@RequestBody ReservaDTO reserva) {
            reservaServiceImp.createReserva(reserva);

            return ResponseEntity.status(201).body("Reservation created");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateReserva(@RequestBody ReservaDTO reservaDTO,
                                           @PathVariable(name = "id") Long id) {
        return reservaServiceImp.updateReserva(reservaDTO, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable(name = "id") Long id) {
        if (reservaServiceImp.findById(id).getReserva_id().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return this.reservaServiceImp.deleteReserva(id);
    }
}
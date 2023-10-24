package com.aeroapp.aeroapp.controller;


import com.aeroapp.aeroapp.Entity.Reservation;
import com.aeroapp.aeroapp.Service.CustomerServicelmp;
import com.aeroapp.aeroapp.Service.ReservationServiceImp;

import com.aeroapp.aeroapp.dto.ReservationDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/reserva")
public class ReservationController {
    @Autowired
    ReservationServiceImp reservationServiceImp;

    @Autowired
    CustomerServicelmp customerServicelmp;

    @Autowired
    public ReservationController(ReservationServiceImp reservationServiceImp, CustomerServicelmp customerServicelmp){
        this.reservationServiceImp = reservationServiceImp;
        this.customerServicelmp = customerServicelmp;
    }

    @GetMapping("")
    public List<Reservation> getAllBookings() {
        reservationServiceImp.updatingCustomerList();
        return this.reservationServiceImp.getAllBookings();
    }
    @GetMapping("/res-number/{res_num}")
    public ReservationDTO getReservationByResN(@PathVariable(name ="res_num") String res_num){
        return reservationServiceImp.findByResN(res_num);
    }
    @GetMapping("/{id}")
    public ReservationDTO getFlightById(@PathVariable(name = "id") Long id){
        reservationServiceImp.updatingCustomerList();
        return this.reservationServiceImp.findById(id);
    }


    @PostMapping("")
    public ResponseEntity createFlight(@RequestBody ReservationDTO reserva) {
            reservationServiceImp.createReserva(reserva);

            return ResponseEntity.status(201).body("Reservation created");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateReserva(@RequestBody ReservationDTO reservationDTO,
                                           @PathVariable(name = "id") Long id) {
        return reservationServiceImp.updateReserva(reservationDTO, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReserva(@PathVariable(name = "id") Long id) {
        if (reservationServiceImp.findById(id).getReserva_id().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return this.reservationServiceImp.deleteReserva(id);
    }
}
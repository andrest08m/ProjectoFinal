package com.aeroapp.aeroapp.controller;



import com.aeroapp.aeroapp.Entity.Flight;
import com.aeroapp.aeroapp.Repository.PlaneRepository;
import com.aeroapp.aeroapp.Service.FlightsServiceImp;
import com.aeroapp.aeroapp.dto.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/flights")
public class FlightController {

    FlightsServiceImp flightsServiceImp;
    PlaneRepository planeRepository;
    @Autowired
    public FlightController(FlightsServiceImp flightsServiceImp, PlaneRepository planeRepository){
        this.flightsServiceImp = flightsServiceImp;
        this.planeRepository = planeRepository;
    }


    @GetMapping()
    public List<Flight> getAllFlights(){
        return this.flightsServiceImp.getAllFlights();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFlightById(@PathVariable(name = "id") Long id){
        return flightsServiceImp.findById(id);
    }

    @PostMapping()
    public ResponseEntity<String> createFlight(@RequestBody FlightDTO vuelo){
        try{
            if(vuelo.getPlane_id().equals("") || vuelo.getPlane_id() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return this.flightsServiceImp.createVueloDTO(vuelo);

        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateFlight(@RequestBody FlightDTO flightDTO,
                                          @PathVariable(name = "id") Long id){

        return flightsServiceImp.updateFlight(flightDTO, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable(name = "id") Long id){

        return this.flightsServiceImp.deleteFlight(id);
    }
}

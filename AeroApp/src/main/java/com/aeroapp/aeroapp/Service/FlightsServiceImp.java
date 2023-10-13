package com.aeroapp.aeroapp.Service;


import com.aeroapp.aeroapp.Entity.Plane;
import com.aeroapp.aeroapp.Entity.Flight;
import com.aeroapp.aeroapp.Repository.PlaneRepository;
import com.aeroapp.aeroapp.Repository.VueloRepository;
import com.aeroapp.aeroapp.dto.FlightDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightsServiceImp {

    @Autowired
    private VueloRepository repository;
    @Autowired
    private PlaneRepository pRepository;



    public ResponseEntity<String> createVueloDTO(FlightDTO flightDTO) {
        Flight flight = mapFromDTO(flightDTO);

        ArrayList<Plane> planes = new ArrayList<>(pRepository.findAll());

        for(Plane plane : planes){
            if(plane.getPlane_code().equals(flight.getPlane_id())){
                Optional<Plane> avionOptional = pRepository.findById(plane.getId_plane());
                if(avionOptional.isPresent()){
                    Plane avion = avionOptional.get();
                    flight.setPlane(avion);
                    flight.setAvailable_seats(avion.getAvailable_seats());
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Plane not found.");
                }
            }
        }

        repository.save(flight);

        return ResponseEntity.status(HttpStatus.CREATED).body("Flight created.");
    }


    public ResponseEntity<Flight> findById(Long id){
        Optional<Flight> vueloPorId = repository.findById(id);

        if(vueloPorId.isPresent()){
            return ResponseEntity.status(200).body(vueloPorId.get());
        }else{
            throw new RuntimeException();
        }
    }

    public List<Flight> getAllFlights(){
        return repository.findAll();
    }

    public ResponseEntity<String> updateFlight(FlightDTO flightDTO, Long id){
        Optional<Flight> vuelo = repository.findById(id);
        if(vuelo.isEmpty()){
            return ResponseEntity.status(404).body("Not found.");
        }
        Flight newInfoFlight = vuelo.get();

        newInfoFlight.setCode(flightDTO.getCode());
        newInfoFlight.setOrigin(flightDTO.getOrigin());
        newInfoFlight.setDestiny(flightDTO.getDestiny());
        newInfoFlight.setDepartureDate(flightDTO.getDepartureDate());
        newInfoFlight.setArrivalDate(flightDTO.getArrivalDate());
        newInfoFlight.setAvailable_seats(flightDTO.getAvailableSeats());
        newInfoFlight.setPlane_id(flightDTO.getPlane_id());
        newInfoFlight.setPrice(flightDTO.getPrice());
        newInfoFlight.setType(flightDTO.getType());
        newInfoFlight.setAirline(flightDTO.getAirline());

        repository.save(newInfoFlight);
        return ResponseEntity.status(200).body("Flight updated successfully.");
    }

    public ResponseEntity<?> deleteFlight(Long id){
        Optional<Flight> vuelo = this.repository.findById(id);
        if(vuelo.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flight deleted");
        }
        this.repository.delete(vuelo.get());

        return ResponseEntity.status(HttpStatus.OK).body("Flight deleted.");

    }

    public FlightDTO mapDTO(Flight flight){
        FlightDTO flightDTO = new FlightDTO();

        flightDTO.setCode(flight.getCode());
        flightDTO.setOrigin(flight.getOrigin());
        flightDTO.setDestiny(flight.getDestiny());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setArrivalDate(flight.getArrivalDate());
        flightDTO.setAvailableSeats(flight.getAvailableSeats());
        flightDTO.setPlane_id(flight.getPlane_id());
        flightDTO.setPrice(flight.getPrice());
        flightDTO.setType(flight.getType());
        flightDTO.setAirline(flight.getAirline());


        return flightDTO;
    }

    public Flight mapFromDTO(FlightDTO flightDTO) {
        Flight flight = new Flight();

        flight.setCode(flightDTO.getCode());
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestiny(flightDTO.getDestiny());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setAvailable_seats(flightDTO.getAvailableSeats());
        flight.setPlane_id(flightDTO.getPlane_id());
        flight.setPrice(flightDTO.getPrice());
        flight.setType(flightDTO.getType());
        flight.setAirline(flightDTO.getAirline());

        return flight;
    }

}

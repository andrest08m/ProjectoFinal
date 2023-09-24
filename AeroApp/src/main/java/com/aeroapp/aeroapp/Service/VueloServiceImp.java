package com.aeroapp.aeroapp.Service;


import com.aeroapp.aeroapp.Entity.Plane;
import com.aeroapp.aeroapp.Entity.Vuelo;
import com.aeroapp.aeroapp.Repository.PlaneRepository;
import com.aeroapp.aeroapp.Repository.VueloRepository;
import com.aeroapp.aeroapp.dto.VueloDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImp implements VueloService{

    @Autowired
    private VueloRepository repository;
    @Autowired
    private PlaneRepository pRepository;



    public ResponseEntity<?> createVueloDTO(VueloDTO vueloDTO) {
        Vuelo vuelo = mapFromDTO(vueloDTO);

        ArrayList<Plane> planes = new ArrayList<>(pRepository.findAll());

        for(Plane plane : planes){
            if(plane.getPlane_code().equals(vuelo.getPlane_id())){
                Optional<Plane> avionOptional = pRepository.findById(plane.getId_plane());
                if(avionOptional.isPresent()){
                    Plane avion = avionOptional.get();
                    vuelo.setPlane(avion);
                    vuelo.setAvailable_seats(avion.getAvailable_seats());
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Plane not found.");
                }
            }
        }


        Vuelo newVuelo = repository.save(vuelo);

        return ResponseEntity.status(HttpStatus.CREATED).body("Flight created.");
    }


    public VueloDTO findById(Long id){
        Optional<Vuelo> vueloPorId = repository.findById(id);

        if(vueloPorId.isPresent()){
            return mapDTO(vueloPorId.get());
        }else{
            throw new RuntimeException();
        }
    }

    public List<Vuelo> getAllFlights(){
        return repository.findAll();
    }

    public ResponseEntity<?> updateFlight(VueloDTO vueloDTO, Long id){
        Optional<Vuelo> vuelo = repository.findById(id);
        if(vuelo.isEmpty()){
            return ResponseEntity.status(404).body("Not found.");
        }
        Vuelo newInfoVuelo = vuelo.get();

        newInfoVuelo.setCode(vueloDTO.getCode());
        newInfoVuelo.setOrigin(vueloDTO.getOrigin());
        newInfoVuelo.setDestiny(vueloDTO.getDestiny());
        newInfoVuelo.setDepartureDate(vueloDTO.getDepartureDate());
        newInfoVuelo.setArrivalDate(vueloDTO.getArrivalDate());
        newInfoVuelo.setAvailable_seats(vueloDTO.getAvailableSeats());
        newInfoVuelo.setPlane_id(vueloDTO.getPlane_id());
        newInfoVuelo.setPrice(vueloDTO.getPrice());
        newInfoVuelo.setType(vueloDTO.getType());
        newInfoVuelo.setAirline(vueloDTO.getAirline());

        repository.save(newInfoVuelo);
        return ResponseEntity.status(200).body("Flight updated successfully.");
    }

    public ResponseEntity<?> deleteFlight(Long id){
        Optional<Vuelo> vuelo = this.repository.findById(id);
        if(vuelo.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Flight deleted");
        }
        this.repository.delete(vuelo.get());

        return ResponseEntity.status(HttpStatus.OK).body("Flight deleted.");

    }

    public VueloDTO mapDTO(Vuelo vuelo){
        VueloDTO vueloDTO = new VueloDTO();

        vueloDTO.setCode(vuelo.getCode());
        vueloDTO.setOrigin(vuelo.getOrigin());
        vueloDTO.setDestiny(vuelo.getDestiny());
        vueloDTO.setDepartureDate(vuelo.getDepartureDate());
        vueloDTO.setArrivalDate(vuelo.getArrivalDate());
        vueloDTO.setAvailableSeats(vuelo.getAvailableSeats());
        vueloDTO.setPlane_id(vuelo.getPlane_id());
        vueloDTO.setPrice(vuelo.getPrice());
        vueloDTO.setType(vuelo.getType());
        vueloDTO.setAirline(vuelo.getAirline());


        return vueloDTO;
    }

    public Vuelo mapFromDTO(VueloDTO vueloDTO) {
        Vuelo vuelo = new Vuelo();

        vuelo.setCode(vueloDTO.getCode());
        vuelo.setOrigin(vueloDTO.getOrigin());
        vuelo.setDestiny(vueloDTO.getDestiny());
        vuelo.setDepartureDate(vueloDTO.getDepartureDate());
        vuelo.setArrivalDate(vueloDTO.getArrivalDate());
        vuelo.setAvailable_seats(vueloDTO.getAvailableSeats());
        vuelo.setPlane_id(vueloDTO.getPlane_id());
        vuelo.setPrice(vueloDTO.getPrice());
        vuelo.setType(vueloDTO.getType());
        vuelo.setAirline(vueloDTO.getAirline());

        return vuelo;
    }

}

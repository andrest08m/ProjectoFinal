package com.aeroapp.aeroapp.Service;

import com.aeroapp.aeroapp.Entity.Plane;
import com.aeroapp.aeroapp.Entity.Reserva;
import com.aeroapp.aeroapp.Repository.PlaneRepository;
import com.aeroapp.aeroapp.dto.PlaneDTO;
import com.aeroapp.aeroapp.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlaneServiceImp {

    @Autowired
    PlaneRepository planeRepository;


    public ResponseEntity<String> createPlane(PlaneDTO planeDTO){
        Plane plane = mapFromDTO(planeDTO);
        if(plane.getAvailable_seats() != 0){
            planeRepository.save(plane);
            return ResponseEntity.status(201).body("Plane created.");

        }else{
            return ResponseEntity.status(404).body("no");
        }

    }

    public PlaneDTO mapDTO(Plane plane){
        PlaneDTO planeDTO = new PlaneDTO();

        planeDTO.setPlane_code(plane.getPlane_code());
        planeDTO.setAirplane_model(plane.getAirplane_model());
        planeDTO.setAvailableSeats(plane.getAvailable_seats());


        return planeDTO;
    }
    public Plane mapFromDTO(PlaneDTO planeDTO) {
       Plane plane = new Plane();

       plane.setPlane_code(planeDTO.getPlane_code());
       plane.setAirplane_model(planeDTO.getAirplane_model());
       plane.setAvailable_seats(planeDTO.getAvailableSeats());

       return plane;
    }
}

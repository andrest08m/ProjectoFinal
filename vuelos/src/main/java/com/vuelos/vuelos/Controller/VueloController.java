package com.vuelos.vuelos.Controller;


import com.vuelos.vuelos.DTO.VueloDTO;
import com.vuelos.vuelos.Entity.Plane;
import com.vuelos.vuelos.Entity.Vuelo;
import com.vuelos.vuelos.Repository.PlaneRepository;
import com.vuelos.vuelos.Service.VueloServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/flights")
public class VueloController {

    VueloServiceImp vueloServiceImp;
    PlaneRepository planeRepository;
    @Autowired
    public VueloController(VueloServiceImp vueloServiceImp, PlaneRepository planeRepository){
        this.vueloServiceImp = vueloServiceImp;
        this.planeRepository = planeRepository;
    }


    @GetMapping("")
    public List<Vuelo> getAllFlights(){
        return this.vueloServiceImp.getAllFlights();
    }
    @PostMapping()
    public ResponseEntity<?> createFlight(@RequestBody VueloDTO vuelo){
        try{
            if(vuelo.getPlane_id().equals("") || vuelo.getPlane_id() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            this.vueloServiceImp.createVueloDTO(vuelo);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}

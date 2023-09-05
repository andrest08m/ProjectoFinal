package com.aeroapp.aeroapp.controller;



import com.aeroapp.aeroapp.Entity.Vuelo;
import com.aeroapp.aeroapp.Repository.PlaneRepository;
import com.aeroapp.aeroapp.Service.VueloServiceImp;
import com.aeroapp.aeroapp.dto.VueloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


    @GetMapping()
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

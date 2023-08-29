package com.vuelos.vuelos.Controller;


import com.vuelos.vuelos.DTO.VueloDTO;
import com.vuelos.vuelos.Entity.Vuelo;
import com.vuelos.vuelos.Service.VueloServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/flights")
public class VueloController {

    @Autowired
    VueloServiceImp vueloServiceImp;


    @GetMapping("")
    public List<Vuelo> getAllFlights(){
        return this.vueloServiceImp.getAllFlights();
    }
    @PostMapping()
    public ResponseEntity createFlight(@RequestBody VueloDTO vuelo){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.vueloServiceImp.createVueloDTO(vuelo));
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

}

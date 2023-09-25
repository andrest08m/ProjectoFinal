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

    @GetMapping("{id}")
    public VueloDTO getFlightById(@PathVariable(name = "id") Long id){
        return vueloServiceImp.findById(id);
    }

    @PostMapping()
    public ResponseEntity<?> createFlight(@RequestBody VueloDTO vuelo){
        try{
            if(vuelo.getPlane_id().equals("") || vuelo.getPlane_id() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return this.vueloServiceImp.createVueloDTO(vuelo);

        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateFlight(@RequestBody VueloDTO vueloDTO,
                                          @PathVariable(name = "id") Long id){

        return vueloServiceImp.updateFlight(vueloDTO, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable(name = "id") Long id){
        if(vueloServiceImp.findById(id).getPlane_id().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return this.vueloServiceImp.deleteFlight(id);
    }
}

package com.aeroapp.aeroapp.controller;


import com.aeroapp.aeroapp.Service.PlaneServiceImp;
import com.aeroapp.aeroapp.dto.PlaneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/plane")
public class PlaneController {

    @Autowired
    PlaneServiceImp planeServiceImp;


    @PostMapping("")
    public ResponseEntity createPlane(@RequestBody PlaneDTO planeDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(planeServiceImp.createPlane(planeDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

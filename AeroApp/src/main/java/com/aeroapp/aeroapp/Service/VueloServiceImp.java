package com.aeroapp.aeroapp.Service;


import com.aeroapp.aeroapp.Entity.Plane;
import com.aeroapp.aeroapp.Entity.Vuelo;
import com.aeroapp.aeroapp.Repository.PlaneRepository;
import com.aeroapp.aeroapp.Repository.VueloRepository;
import com.aeroapp.aeroapp.dto.VueloDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImp implements VueloService{

    @Autowired
    private VueloRepository repository;
    @Autowired
    private PlaneRepository pRepository;

    public String createVueloDTO(VueloDTO vueloDTO) {
        Vuelo vuelo = mapFromDTO(vueloDTO);

        Optional<Plane> avionOptional = pRepository.findById(vuelo.getPlane_id());
        if(avionOptional.isPresent()){
            Plane avion = avionOptional.get();
            vuelo.setPlane(avion);
            vuelo.setAvailable_seats(avion.getAvailableSeats());
        }else{
            return "Plane not available.";
        }

        Vuelo newVuelo = repository.save(vuelo);


        return "Flight created successfully";
    }


    public List<Vuelo> getAllFlights(){
        return repository.findAll();
    }

    public VueloDTO mapDTO(Vuelo vuelo){
        VueloDTO vueloDTO = new VueloDTO();

        vueloDTO.setCode(vuelo.getCode());
        vueloDTO.setOrigin(vuelo.getOrigin());
        vueloDTO.setDestiny(vuelo.getDestiny());
        vueloDTO.setPlane_id(vuelo.getPlane_id());
        vueloDTO.setDepartureDate(vuelo.getDepartureDate());
        vueloDTO.setArrivalDate(vuelo.getArrivalDate());
        vueloDTO.setAvailableSeats(vuelo.getAvailableSeats());
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
        vuelo.setPlane_id(vueloDTO.getPlane_id());
        vuelo.setArrivalDate(vueloDTO.getArrivalDate());
        vuelo.setAvailable_seats(vueloDTO.getAvailableSeats());
        vuelo.setPrice(vueloDTO.getPrice());
        vuelo.setType(vueloDTO.getType());
        vuelo.setAirline(vueloDTO.getAirline());

        return vuelo;
    }

}

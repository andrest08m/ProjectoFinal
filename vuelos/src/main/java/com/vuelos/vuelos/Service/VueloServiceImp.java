package com.vuelos.vuelos.Service;

import com.vuelos.vuelos.DTO.VueloDTO;
import com.vuelos.vuelos.Entity.Plane;
import com.vuelos.vuelos.Entity.Vuelo;
import com.vuelos.vuelos.Repository.PlaneRepository;
import com.vuelos.vuelos.Repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServiceImp implements VueloService{

    @Autowired
    private VueloRepository repository;
    @Autowired
    private PlaneRepository pRepository;

    @Override
    public VueloDTO createVueloDTO(VueloDTO vueloDTO) {
        Vuelo vuelo = mapFromDTO(vueloDTO);

        Plane p = vuelo.getPlane();
        pRepository.save(p);
        Vuelo newVuelo = repository.save(vuelo);


        return mapDTO(newVuelo);
    }


    public List<Vuelo> getAllFlights(){
        return repository.findAll();
    }

    public VueloDTO mapDTO(Vuelo vuelo){
        VueloDTO vueloDTO = new VueloDTO();

        vueloDTO.setCode(vuelo.getCode());
        vueloDTO.setOrigin(vuelo.getOrigin());
        vueloDTO.setDestiny(vuelo.getDestiny());
        vueloDTO.setDepartureDate(vuelo.getDepartureDate());
        vueloDTO.setArrivalDate(vuelo.getArrivalDate());
        vueloDTO.setAvailableSeats(vuelo.getAvailable_seats());
        vueloDTO.setPrice(vuelo.getPrice());
        vueloDTO.setType(vuelo.getType());
        vueloDTO.setAirline(vuelo.getAirline());
        vueloDTO.setPlane(vuelo.getPlane());


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
        vuelo.setPrice(vueloDTO.getPrice());
        vuelo.setType(vueloDTO.getType());
        vuelo.setAirline(vueloDTO.getAirline());
        vuelo.setPlane(vueloDTO.getPlane());

        return vuelo;
    }

}

package com.vuelos.vuelos.Service;

import com.vuelos.vuelos.DTO.VueloDTO;
import com.vuelos.vuelos.Entity.Vuelo;
import com.vuelos.vuelos.Repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServiceImp implements VueloService{

    @Autowired
    private VueloRepository repository;

    @Override
    public VueloDTO createVueloDTO(VueloDTO vueloDTO) {
        Vuelo vuelo = mapFromDTO(vueloDTO);

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
        vueloDTO.setPrice(vuelo.getPrice());
        vueloDTO.setAvailableSeats(vuelo.getAvailableSeats());
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
        vuelo.setPrice(vueloDTO.getPrice());
        vuelo.setAvailableSeats(vueloDTO.getAvailableSeats());
        vuelo.setType(vueloDTO.getType());
        vuelo.setAirline(vueloDTO.getAirline());

        return vuelo;
    }

}

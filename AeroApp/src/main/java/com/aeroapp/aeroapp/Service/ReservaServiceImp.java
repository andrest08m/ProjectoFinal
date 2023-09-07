package com.aeroapp.aeroapp.Service;


import com.aeroapp.aeroapp.Entity.Reserva;
import com.aeroapp.aeroapp.Repository.ReservaRepository;

import com.aeroapp.aeroapp.dto.ReservaDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImp implements ReservaService{

@Autowired
    private ReservaRepository repository;

    public List<Reserva> getAllBookings() {
        return repository.findAll();
    }


    public ReservaDTO createReservaDTO(ReservaDTO reserva) {
        Reserva reserva1 = mapFromDTO(reserva);
        Reserva newReserva = repository.save(reserva1);
        return mapDTO(newReserva);
    }
    public ReservaDTO mapDTO(Reserva reserva){
        ReservaDTO reservaDTO = new ReservaDTO();

        reservaDTO.setReservation_number(reserva.getReservation_number());
        reservaDTO.setReservation_day(reserva.getReservation_day());
        reservaDTO.setReservation_time(reserva.getReservation_time());
        reservaDTO.setId_customers(reserva.getId_customers());
        reservaDTO.setClass_type(reserva.getClass_type());
        reservaDTO.setId_flight(reserva.getId_flight());

        return reservaDTO;
    }
    public Reserva mapFromDTO(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setReservation_number(reservaDTO.getReservation_number());
        reserva.setReservation_day(reservaDTO.getReservation_day());
        reserva.setReservation_time(reservaDTO.getReservation_time());
        reserva.setId_customers(reservaDTO.getId_customers());
        reserva.setClass_type(reservaDTO.getClass_type());
        reserva.setId_flight(reservaDTO.getId_flight());
        return reserva;
    }
}


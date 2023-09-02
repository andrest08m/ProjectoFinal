package com.reservas.reservas.Service;


import com.reservas.reservas.DTO.ReservaDTO;
import com.reservas.reservas.Entity.Reserva;
import com.reservas.reservas.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImp {

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
        reservaDTO.setReservation_number(reservaDTO.getReservation_number());
        reservaDTO.setReservation_day(reservaDTO.getReservation_day());
        reservaDTO.setReservation_time(reservaDTO.getReservation_time());
        reservaDTO.setId_customers(reservaDTO.getId_customers());
        reservaDTO.setClass_type(reservaDTO.getClass_type());
        reservaDTO.setId_flight(reservaDTO.getId_flight());
        return reserva;
    }
}


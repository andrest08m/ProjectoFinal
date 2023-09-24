package com.aeroapp.aeroapp.Service;


import com.aeroapp.aeroapp.Entity.Customer;
import com.aeroapp.aeroapp.Entity.Plane;
import com.aeroapp.aeroapp.Entity.Reserva;
import com.aeroapp.aeroapp.Entity.Vuelo;
import com.aeroapp.aeroapp.Repository.CustomerRepository;
import com.aeroapp.aeroapp.Repository.ReservaRepository;

import com.aeroapp.aeroapp.dto.ReservaDTO;


import com.aeroapp.aeroapp.dto.VueloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservaServiceImp implements ReservaService{

@Autowired
    private ReservaRepository rpository;


@Autowired
private CustomerRepository customerRepository;

    public ResponseEntity<?> createReserva(ReservaDTO reservaDTO) {
        Reserva reserva = mapFromDTO(reservaDTO);
        List<Customer> reservaOptional = customerRepository.findAll();

        reserva.setClientes((List<Customer>) reservaOptional);
        Reserva newReserva = rpository.save(reserva);

        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation created.");
    }

    public ReservaDTO createReservaDTO(ReservaDTO reserva) {
        Reserva reserva1 = mapFromDTO(reserva);
        Reserva newReserva = rpository.save(reserva1);
        return mapDTO(newReserva);
    }
        public ReservaDTO findById(Long id){
            Optional<Reserva> ReservaPorId = rpository.findById(id);

            if(ReservaPorId.isPresent()){
                return mapDTO(ReservaPorId.get());
            }else{
                throw new RuntimeException();
            }
        }


    public List<Reserva> getAllBookings() {
        return rpository.findAll();
    }

    public ResponseEntity<?> updateReserva(ReservaDTO reservaDTO, Long id){
        Optional<Reserva> reserva = rpository.findById(id);
        if(reserva.isEmpty()){
            return ResponseEntity.status(404).body("Not found.");
        }
        Reserva newInfoReserva = reserva.get();

        newInfoReserva.setReservation_number(reservaDTO.getReservation_number());
        newInfoReserva.setReservation_day(reservaDTO.getReservation_day());
        newInfoReserva.setReservation_time(reservaDTO.getReservation_time());
        newInfoReserva.setClass_type(reservaDTO.getClass_type());
        newInfoReserva.setReservation_id(reservaDTO.getReserva_id());


        rpository.save(newInfoReserva);
        return ResponseEntity.status(200).body("Reservation updated successfully.");
    }



    public ResponseEntity<?> deleteReserva(Long id){
        Optional<Reserva> reserva = this.rpository.findById(id);
        if(reserva.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reservation deleted");
        }
        this.rpository.delete(reserva.get());

        return ResponseEntity.status(HttpStatus.OK).body("Reservation deleted.");
    }


    public ReservaDTO mapDTO(Reserva reserva){
        ReservaDTO reservaDTO = new ReservaDTO();

        reservaDTO.setReservation_number(reserva.getReservation_number());
        reservaDTO.setReservation_day(reserva.getReservation_day());
        reservaDTO.setReservation_time(reserva.getReservation_time());
        reservaDTO.setReserva_id(reserva.getReservation_id());


        return reservaDTO;
    }
    public Reserva mapFromDTO(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setReservation_number(reservaDTO.getReservation_number());
        reserva.setReservation_day(reservaDTO.getReservation_day());
        reserva.setReservation_time(reservaDTO.getReservation_time());
        reserva.setClass_type(reservaDTO.getClass_type());


        return reserva;
    }
}


package com.aeroapp.aeroapp.Service;


import com.aeroapp.aeroapp.Entity.Customer;
import com.aeroapp.aeroapp.Entity.Reserva;
import com.aeroapp.aeroapp.Entity.Vuelo;
import com.aeroapp.aeroapp.Repository.CustomerRepository;
import com.aeroapp.aeroapp.Repository.ReservaRepository;

import com.aeroapp.aeroapp.Repository.VueloRepository;
import com.aeroapp.aeroapp.dto.ReservaDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservaServiceImp implements ReservaService{

@Autowired
    private ReservaRepository rpository;
@Autowired
    private VueloRepository vueloRepository;
@Autowired
    private ReservaRepository reservaRepository;


@Autowired
private CustomerRepository customerRepository;


//            GENERATE DATA METHODS

    public String createReservationId(){
        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 10;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public void updatingCustomerList(){
        List<Reserva> listOfBookings = rpository.findAll();
        List<Customer> listOfCustomers = customerRepository.findAll();
        List<Customer> customersPerBooking = new ArrayList<>();

        for(Reserva booking : listOfBookings){
            for(Customer customer : listOfCustomers){
                if(customer.getCustomer_reservation() == booking.getReservation_number()){
                    customersPerBooking.add(customer);
                }
            }
            booking.setClientes(customersPerBooking);

        }
    }

//                    METHODS FOR CONTROLLER

    public List<Reserva> getAllBookings() {
        return rpository.findAll();
    }

    public ReservaDTO findById(Long id){
        Optional<Reserva> ReservaPorId = rpository.findById(id);

        if(ReservaPorId.isPresent()){
            return mapDTO(ReservaPorId.get());
        }else{
            throw new RuntimeException();
        }
    }

    public ResponseEntity<?> createReserva(ReservaDTO reservaDTO) {
        Reserva reserva = mapFromDTO(reservaDTO);

        List<Vuelo> listOfFlights = vueloRepository.findAll();

        reserva.setReservation_id(createReservationId());


        for(Vuelo vuelo : listOfFlights){
            if(reserva.getPlane_code().equals(vuelo.getPlane().getPlane_code())){
                reserva.setFlight_code(vuelo);
            }
        }


        rpository.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation created.");
    }


    public ResponseEntity<?> updateReserva(ReservaDTO reservaDTO, Long id){
        Optional<Reserva> reserva = rpository.findById(id);
        if(reserva.isEmpty()){
            return ResponseEntity.status(404).body("Not found.");
        }
        Reserva newInfoReserva = reserva.get();

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

        reservaDTO.setReservation_day(reserva.getReservation_day());
        reservaDTO.setReservation_time(reserva.getReservation_time());
        reservaDTO.setClass_type(reserva.getClass_type());
        reservaDTO.setPlane_code(reserva.getPlane_code());

        return reservaDTO;
    }
    public Reserva mapFromDTO(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();

        reserva.setReservation_day(reservaDTO.getReservation_day());
        reserva.setReservation_time(reservaDTO.getReservation_time());
        reserva.setClass_type(reservaDTO.getClass_type());
        reserva.setPlane_code(reservaDTO.getPlane_code());

        return reserva;
    }
}


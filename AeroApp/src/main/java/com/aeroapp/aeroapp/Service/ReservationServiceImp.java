package com.aeroapp.aeroapp.Service;


import com.aeroapp.aeroapp.Entity.Customer;
import com.aeroapp.aeroapp.Entity.Reservation;
import com.aeroapp.aeroapp.Entity.Flight;
import com.aeroapp.aeroapp.Repository.CustomerRepository;
import com.aeroapp.aeroapp.Repository.ReservaRepository;

import com.aeroapp.aeroapp.Repository.VueloRepository;
import com.aeroapp.aeroapp.dto.ReservationDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationServiceImp {

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
        List<Reservation> listOfBookings = rpository.findAll();
        List<Customer> listOfCustomers = customerRepository.findAll();
        List<Customer> customersPerBooking = new ArrayList<>();

        for(Reservation booking : listOfBookings){
            for(Customer customer : listOfCustomers){
                if(customer.getCustomer_reservation() == booking.getReservation_number()){
                    customersPerBooking.add(customer);
                }
            }
            booking.setClientes(customersPerBooking);

        }
    }

//                    METHODS FOR CONTROLLER

    public List<Reservation> getAllBookings() {
        return rpository.findAll();
    }

    public ReservationDTO findById(Long id){
        Optional<Reservation> ReservaPorId = rpository.findById(id);

        if(ReservaPorId.isPresent()){
            return mapDTO(ReservaPorId.get());
        }else{
            throw new RuntimeException();
        }
    }

    public ReservationDTO findByResN(String res_number){
        List<Reservation> listOfBookings = rpository.findAll();

        for(Reservation res : listOfBookings){
            if(res_number.equals(res.getReservation_id())){
                return mapDTO(res);
            }
        }
        return null;
    }

    public ResponseEntity<String> createReserva(ReservationDTO reservationDTO) {
        Reservation reservation = mapFromDTO(reservationDTO);

        List<Flight> listOfFlights = vueloRepository.findAll();

        reservation.setReservation_id(createReservationId());


        for(Flight flight : listOfFlights){
            if(reservation.getPlane_code().equals(flight.getPlane().getPlane_code())){
                reservation.setFlight_code(flight);
            }
        }


        rpository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation created.");
    }


    public ResponseEntity<String> updateReserva(ReservationDTO reservationDTO, Long id){
        Optional<Reservation> reserva = rpository.findById(id);
        if(reserva.isEmpty()){
            return ResponseEntity.status(404).body("Not found.");
        }
        Reservation newInfoReservation = reserva.get();

        newInfoReservation.setReservation_day(reservationDTO.getReservation_day());
        newInfoReservation.setReservation_time(reservationDTO.getReservation_time());
        newInfoReservation.setClass_type(reservationDTO.getClass_type());
        newInfoReservation.setReservation_id(reservationDTO.getReserva_id());


        rpository.save(newInfoReservation);
        return ResponseEntity.status(200).body("Reservation updated successfully.");
    }



    public ResponseEntity<?> deleteReserva(Long id){
        Optional<Reservation> reserva = this.rpository.findById(id);
        if(reserva.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reservation deleted");
        }
        this.rpository.delete(reserva.get());

        return ResponseEntity.status(HttpStatus.OK).body("Reservation deleted.");
    }


    public ReservationDTO mapDTO(Reservation reservation){
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setReservation_day(reservation.getReservation_day());
        reservationDTO.setReservation_time(reservation.getReservation_time());
        reservationDTO.setClass_type(reservation.getClass_type());
        reservationDTO.setPlane_code(reservation.getPlane_code());

        return reservationDTO;
    }
    public Reservation mapFromDTO(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        reservation.setReservation_day(reservationDTO.getReservation_day());
        reservation.setReservation_time(reservationDTO.getReservation_time());
        reservation.setClass_type(reservationDTO.getClass_type());
        reservation.setPlane_code(reservationDTO.getPlane_code());

        return reservation;
    }
}


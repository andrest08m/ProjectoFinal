package com.aeroapp.aeroapp.dto;

import com.aeroapp.aeroapp.Entity.Customer;
import com.aeroapp.aeroapp.Entity.Vuelo;

import java.time.LocalDateTime;
import java.util.Set;

public class ReservaDTO {

    private LocalDateTime reservation_day;
    private LocalDateTime reservation_time;
    private String class_type;
    private Vuelo flight_code;
    private String reserva_id;
    private byte reserva_disponible;
    private Set<Customer> clientes;
    private String plane_code;


    public ReservaDTO() {
    }

    public ReservaDTO(Long reservation_number, LocalDateTime reservation_day, LocalDateTime reservation_time,
                      String class_type, Vuelo flight_code, String reserva_id, byte reserva_disponible,
                      String plane_code,
                      Set<Customer> clientes) {
        this.reservation_day = reservation_day;
        this.reservation_time = reservation_time;
        this.class_type = class_type;
        this.flight_code = flight_code;
        this.reserva_id = reserva_id;
        this.reserva_disponible = reserva_disponible;
        this.clientes = clientes;
    }



    public LocalDateTime getReservation_day() {
        return reservation_day;
    }

    public void setReservation_day(LocalDateTime reservation_day) {
        this.reservation_day = reservation_day;
    }

    public LocalDateTime getReservation_time() {
        return reservation_time;
    }

    public void setReservation_time(LocalDateTime reservation_time) {
        this.reservation_time = reservation_time;
    }

    public String getClass_type() {
        return class_type;
    }

    public void setClass_type(String class_type) {
        this.class_type = class_type;
    }

    public Vuelo getFlight_code() {
        return flight_code;
    }

    public void setFlight_code(Vuelo flight_code) {
        this.flight_code = flight_code;
    }

    public Set<Customer> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Customer> clientes) {
        this.clientes = clientes;
    }

    public String getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(String reserva_id) {
        this.reserva_id = reserva_id;
    }

    public byte getReservaDisponible() {
        return reserva_disponible;
    }

    public void setReservaDisponible(byte reserva_disponible) {
        this.reserva_disponible = reserva_disponible;
    }

    public String getPlane_code() {
        return plane_code;
    }

    public void setPlane_code(String plane_code) {
        this.plane_code = plane_code;
    }
}

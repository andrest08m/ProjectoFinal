package com.aeroapp.aeroapp.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long reservation_number;
    private LocalDateTime reservation_day;
    private LocalDateTime reservation_time;
    private String class_type;
    private String reservation_id;
    private byte reserva_disponible;
    private String plane_code;

    @ManyToOne
    @JoinColumn(name = "flight_code")
    @JsonIgnore
    private Flight flight_code;
    @OneToMany(mappedBy = "reservation")
    private List<Customer> clientes;

    public Reservation() {
    }

    public Reservation(Long reservation_number, LocalDateTime reservation_day, LocalDateTime reservation_time,
                       String class_type, String reservation_id, byte reserva_disponible, String plane_code,
                       Flight flight_code,
                       List<Customer> clientes) {
        this.reservation_number = reservation_number;
        this.reservation_day = reservation_day;
        this.reservation_time = reservation_time;
        this.class_type = class_type;
        this.reservation_id = reservation_id;
        this.reserva_disponible = reserva_disponible;
        this.plane_code = plane_code;
        this.flight_code = flight_code;
        this.clientes = clientes;
    }

    public long getReservaDisponible() {
        return reserva_disponible;
    }

    public void setReservaDisponible(byte reservaDisponible) {
        this.reserva_disponible = reservaDisponible;
    }

    public Long getReservation_number() {
        return reservation_number;
    }

    public void setReservation_number(Long reservation_number) {
        this.reservation_number = reservation_number;
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

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }

    public List<Customer> getClientes() {
        return clientes;
    }

    public void setClientes(List<Customer> clientes) {
        this.clientes = clientes;
    }

    public Flight getFlight_code() {
        return flight_code;
    }

    public void setFlight_code(Flight flight_code) {
        this.flight_code = flight_code;
    }

    public String getPlane_code() {
        return plane_code;
    }

    public void setPlane_code(String plane_code) {
        this.plane_code = plane_code;
    }
}
package com.reservas.reservas.Entity;


import javax.naming.Name;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bookings")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservation_number;
    private LocalDateTime reservation_day;
    private LocalDateTime reservation_time;
    private int id_customers;
    private String class_type;
    private int id_flight;

    @OneToMany(mappedBy = "reserva")
    private List<Cliente> clientes;

    public Reserva() {
    }

    public Reserva(int reservation_number, LocalDateTime reservation_day, LocalDateTime reservation_time,
                   int id_customers, String class_type, int id_flight, List<Cliente> clientes) {
        this.reservation_number = reservation_number;
        this.reservation_day = reservation_day;
        this.reservation_time = reservation_time;
        this.id_customers = id_customers;
        this.class_type = class_type;
        this.id_flight = id_flight;
        this.clientes = clientes;
    }

    public int getReservation_number() {
        return reservation_number;
    }

    public void setReservation_number(int reservation_number) {
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

    public int getId_customers() {
        return id_customers;
    }

    public void setId_customers(int id_customers) {
        this.id_customers = id_customers;
    }

    public String getClass_type() {
        return class_type;
    }

    public void setClass_type(String class_type) {
        this.class_type = class_type;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}

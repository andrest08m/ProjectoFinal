package com.reservas.reservas.Entity;


import javax.naming.Name;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bookings")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false, updatable = false)

    private int Reservation_number;
    private LocalDateTime reservation_day;
    private LocalDateTime reservation_time;
    private int id_customers;
    private String clasS;
    private int id_flight;

    public Reserva() {
    }

    public Reserva(int reservation_number, LocalDateTime reservation_day, LocalDateTime reservation_time,
                      int id_customers, String clasS, int id_flight) {
        Reservation_number = reservation_number;
        this.reservation_day = reservation_day;
        this.reservation_time = reservation_time;
        this.id_customers = id_customers;
        this.clasS = clasS;
        this.id_flight = id_flight;
    }

    public int getReservation_number() {
        return Reservation_number;
    }

    public void setReservation_number(int reservation_number) {
        Reservation_number = reservation_number;
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

    public String getClasS() {
        return clasS;
    }

    public void setClasS(String clasS) {
        this.clasS = clasS;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

}

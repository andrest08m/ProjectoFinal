package com.aeroapp.aeroapp.Entity;



import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String class_type;

    private String reservation_id;
    private String ReservaDisponible;

    @ManyToOne
    @JoinColumn(name = "code")
    private Vuelo flight_code;

    @OneToMany(mappedBy = "reserva")
    private List<Customer> clientes;

    public Reserva() {
    }

    public Reserva(int reservation_number, LocalDateTime reservation_day, LocalDateTime reservation_time,
                   String class_type, String reservation_id, String reservaDisponible, Vuelo flight_code,
                   List<Customer> clientes) {
        this.reservation_number = reservation_number;
        this.reservation_day = reservation_day;
        this.reservation_time = reservation_time;
        this.class_type = class_type;
        this.reservation_id = reservation_id;
        ReservaDisponible = reservaDisponible;
        this.flight_code = flight_code;
        this.clientes = clientes;
    }

    public String getReservaDisponible() {
        return ReservaDisponible;
    }

    public void setReservaDisponible(String reservaDisponible) {
        ReservaDisponible = reservaDisponible;
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

    public Vuelo getFlight_code() {
        return flight_code;
    }

    public void setFlight_code(Vuelo flight_code) {
        this.flight_code = flight_code;
    }
}

package com.aeroapp.aeroapp.Entity;




import com.aeroapp.aeroapp.Enums.Airline;
import com.aeroapp.aeroapp.Enums.FlightType;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="flight")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id_flight;
    private String code;
    private String origin;
    private String destiny;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String plane_id;
    private int available_seats;
    private double price;
    @OneToOne
    @JoinColumn(name = "plane")
    private Plane plane;
    @Enumerated(EnumType.STRING)
    private FlightType type;
    @Enumerated(EnumType.STRING)
    private Airline airline;

    public Vuelo() {
    }

    public Vuelo(Long id_flight, String code, String origin, String destiny,
                 LocalDateTime departureDate, LocalDateTime arrivalDate, String plane_id,
                 int available_seats, double price, Plane plane, FlightType type, Airline airline) {
        this.id_flight = id_flight;
        this.code = code;
        this.origin = origin;
        this.destiny = destiny;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.plane_id = plane_id;
        this.available_seats = available_seats;
        this.price = price;
        this.plane = plane;
        this.type = type;
        this.airline = airline;
    }

    public int getAvailableSeats() {
        if(plane != null){
            return this.plane.getAvailableSeats();
        }

        return 0;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public Long getId_flight() {
        return id_flight;
    }

    public void setId_flight(Long id_flight) {
        this.id_flight = id_flight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(String plane_id) {
        this.plane_id = plane_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public FlightType getType() {
        return type;
    }

    public void setType(FlightType type) {
        this.type = type;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}

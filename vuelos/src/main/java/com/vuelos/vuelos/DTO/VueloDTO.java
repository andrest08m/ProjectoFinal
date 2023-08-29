package com.vuelos.vuelos.DTO;

import com.vuelos.vuelos.Enums.Airline;
import com.vuelos.vuelos.Enums.FlightType;

import java.time.LocalDateTime;

public class VueloDTO {

    private Long code;
    private String origin;
    private String destiny;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private double price;
    private int availableSeats;
    private FlightType type;
    private Airline airline;

    public VueloDTO() {
    }

    public VueloDTO(Long code, String origin, String destiny, LocalDateTime departureDate,
                    LocalDateTime arrivalDate, double price, int availableSeats, FlightType type, Airline airline) {
        this.code = code;
        this.origin = origin;
        this.destiny = destiny;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
        this.availableSeats = availableSeats;
        this.type = type;
        this.airline = airline;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
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

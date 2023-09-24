package com.aeroapp.aeroapp.dto;


import com.aeroapp.aeroapp.Entity.Plane;
import com.aeroapp.aeroapp.Entity.Reserva;
import com.aeroapp.aeroapp.utils.Airline;
import com.aeroapp.aeroapp.utils.FlightType;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class VueloDTO {

    private String code;
    private String origin;
    private String destiny;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private double price;
    private String plane_id;
    private int availableSeats;
    private FlightType type;
    private Airline airline;
    private Plane plane;
    private Set<Reserva> clientes;


    public VueloDTO() {
    }

    public VueloDTO(String code, String origin, String destiny, LocalDateTime departureDate,
                    LocalDateTime arrivalDate, double price, String plane_id,
                    int availableSeats, FlightType type, Airline airline, Plane plane, Set<Reserva> clientes) {
        this.code = code;
        this.origin = origin;
        this.destiny = destiny;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.price = price;
        this.plane_id = plane_id;
        this.availableSeats = availableSeats;
        this.type = type;
        this.airline = airline;
        this.plane = plane;
        this.clientes = clientes;
    }

    public int getAvailableSeats() {
        if(plane != null){
            List<Integer> clientsAboard =
                    getClientes().stream().map(fn -> fn.getClientes().size()).collect(Collectors.toList());
            return this.plane.getAvailable_seats() - clientsAboard.size();
        }

        return 0;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(String plane_id) {
        this.plane_id = plane_id;
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

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Set<Reserva> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Reserva> clientes) {
        this.clientes = clientes;
    }
}

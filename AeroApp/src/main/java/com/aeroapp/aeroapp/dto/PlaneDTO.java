package com.aeroapp.aeroapp.dto;

import com.aeroapp.aeroapp.Entity.Vuelo;

public class PlaneDTO {
    private String plane_code;
    private String airplane_model;
    private int available_seats;
    private Vuelo flight;

    public PlaneDTO() {
    }

    public PlaneDTO(String plane_code, String airplane_model, int available_seats, Vuelo flight) {
        this.plane_code = plane_code;
        this.airplane_model = airplane_model;
        this.available_seats = available_seats;
        this.flight = flight;
    }

    public String getPlane_code() {
        return plane_code;
    }

    public void setPlane_code(String plane_code) {
        this.plane_code = plane_code;
    }

    public String getAirplane_model() {
        return airplane_model;
    }

    public void setAirplane_model(String airplane_model) {
        this.airplane_model = airplane_model;
    }

    public int getAvailableSeats() {
        return available_seats;
    }

    public void setAvailableSeats(int available_seats) {
        this.available_seats = available_seats;
    }

    public Vuelo getFlight() {
        return flight;
    }

    public void setFlight(Vuelo flight) {
        this.flight = flight;
    }
}

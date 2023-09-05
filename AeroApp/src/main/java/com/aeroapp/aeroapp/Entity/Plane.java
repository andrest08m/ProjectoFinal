package com.aeroapp.aeroapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "plane")
public class Plane {

    @Id
    @Column(name= "registration_id")
    private String id_plane;
    private String airplane_model;
    private int availableSeats;
    @OneToOne(mappedBy = "plane")
    @JsonIgnore
    private Vuelo flight;

    public Plane() {
    }

    public Plane(String id_plane, String airplane_model, int availableSeats, Vuelo flight) {
        this.id_plane = id_plane;
        this.airplane_model = airplane_model;
        this.availableSeats = availableSeats;
        this.flight = flight;
    }

    public String getId_plane() {
        return id_plane;
    }

    public void setId_plane(String id_plane) {
        this.id_plane = id_plane;
    }

    public String getAirplane_model() {
        return airplane_model;
    }

    public void setAirplane_model(String airplane_model) {
        this.airplane_model = airplane_model;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Vuelo getFlight() {
        return flight;
    }

    public void setFlight(Vuelo flight) {
        this.flight = flight;
    }
}

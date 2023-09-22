package com.aeroapp.aeroapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id_plane;
    private String plane_code;
    private String airplane_model;
    private int availableSeats;
    @OneToOne(mappedBy = "plane")
    @JsonIgnore
    private Vuelo flight;

    public Plane() {
    }

    public Plane(Long id_plane, String plane_code, String airplane_model, int availableSeats, Vuelo flight) {
        this.id_plane = id_plane;
        this.plane_code = plane_code;
        this.airplane_model = airplane_model;
        this.availableSeats = availableSeats;
        this.flight = flight;
    }

    public Long getId_plane() {
        return id_plane;
    }

    public String getPlane_code() {
        return plane_code;
    }

    public void setPlane_code(String plane_code) {
        this.plane_code = plane_code;
    }

    public void setId_plane(Long id_plane) {
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

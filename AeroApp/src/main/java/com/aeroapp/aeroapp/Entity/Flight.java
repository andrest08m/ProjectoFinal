package com.aeroapp.aeroapp.Entity;




import com.aeroapp.aeroapp.utils.Airline;
import com.aeroapp.aeroapp.utils.FlightType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name="flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id_flight;
    private String code;
    private String origin;
    private String destiny;
    private Date departureDate;
    private Date arrivalDate;
    private String plane_id;
    private int available_seats;
    private double price;
    @OneToOne
    @JoinColumn(name = "flight")
    private Plane plane;
    @OneToMany(mappedBy = "flight_code")
    private Set<Reservation> reservations;

    @Enumerated(EnumType.STRING)
    private FlightType type;
    @Enumerated(EnumType.STRING)
    private Airline airline;


    public Flight() {
    }

    public Flight(Long id_flight, String code, String origin, String destiny,
                  Date departureDate, Date arrivalDate, String plane_id,
                  int available_seats, double price, Plane plane, Set<Reservation> reservations,
                  FlightType type, Airline airline) {
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
        this.reservations = reservations;
        this.type = type;
        this.airline = airline;
    }

    public Long getId_flight() {
        return id_flight;
    }

    public void setId_flight(Long id_flight) {
        this.id_flight = id_flight;
    }

    public int getAvailableSeats() {
        if(plane != null){
            List<Integer> clientsAboard =
                    getCustomer().stream().map(fn -> fn.getClientes().size()).collect(Collectors.toList());
            return this.plane.getAvailable_seats() - clientsAboard.size();
        }

        return 0;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
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
    public Date getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
    public Date getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(Date arrivalDate) {
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
    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Plane getPlane() {
        return plane;
    }

    public Set<Reservation> getCustomer() {
        return reservations;
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

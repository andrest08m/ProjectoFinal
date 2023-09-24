package com.aeroapp.aeroapp.Entity;


import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_customer;
    private String name;
    private String last_name;
    private Long cell_phone;
    private String gender;
    @OneToMany(mappedBy = "customer")
    private Set<Vuelo> vuelo;

    @JoinColumn(name = "reservation_id", nullable = false)
    private Reserva reserva;


    public Customer() {
    }


    public Customer(Long id_customer, String name, String last_name, Long cell_phone,
                    String gender, Set<Vuelo> vuelo, Reserva reserva) {
        this.id_customer = id_customer;
        this.name = name;
        this.last_name = last_name;
        this.cell_phone = cell_phone;
        this.gender = gender;
        this.vuelo = vuelo;
        this.reserva = reserva;
    }

    public Long getId_customer() {
        return id_customer;
    }

    public void setId_customer(Long id_customer) {
        this.id_customer = id_customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(Long cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Vuelo> getVuelo() {
        return this.vuelo;
    }

    public void setVuelo(Set<Vuelo> vuelo) {
        this.vuelo = vuelo;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}

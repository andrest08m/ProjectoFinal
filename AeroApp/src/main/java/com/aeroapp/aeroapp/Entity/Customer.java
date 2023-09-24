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
    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "reserva_id", nullable = false)
    private  Reserva reserva;

    @OneToMany(mappedBy = "customer")
    private Set<Vuelo> Vuelo;
=======
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reserva reserva;
>>>>>>> f43eb70080cfec38446fb45ef1bedbe9397574fc

    public Customer() {
    }
<<<<<<< HEAD
    public Customer(int id_customer, String name, String last_name, Long cell_phone, String gender, Reserva reserva) {
=======

    public Customer(Long id_customer, String name, String last_name, Long cell_phone,
                    String gender, Reserva reserva) {
>>>>>>> f43eb70080cfec38446fb45ef1bedbe9397574fc
        this.id_customer = id_customer;
        this.name = name;
        this.last_name = last_name;
        this.cell_phone = cell_phone;
        this.gender = gender;
        this.reserva = reserva;
    }
<<<<<<< HEAD
    public int getId_customer() {
=======

    public Long getId_customer() {
>>>>>>> f43eb70080cfec38446fb45ef1bedbe9397574fc
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

<<<<<<< HEAD

=======
>>>>>>> f43eb70080cfec38446fb45ef1bedbe9397574fc
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


    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

}

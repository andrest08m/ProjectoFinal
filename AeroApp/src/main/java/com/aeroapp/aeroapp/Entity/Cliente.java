package com.aeroapp.aeroapp.Entity;


import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "customer_id")
    private int id_customer;
    private String name;
    private String last_name;
    private Long cell_phone;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private  Reserva reserva;


    public Cliente() {
    }

    public Cliente(int id_customer, String name, String last_name, Long cell_phone, String gender, Reserva reserva) {
        this.id_customer = id_customer;
        this.name = name;
        this.last_name = last_name;
        this.cell_phone = cell_phone;
        this.gender = gender;
        this.reserva = reserva;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
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

    public Reserva getReservas(){
        return reserva;
    }

    public void setReserva(Reserva reserva){
        this.reserva = reserva;
    }

}

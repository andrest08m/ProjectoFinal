package com.reservas.reservas.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Cliente {
    @Id
    @Column(name= "Customer_id")

    private int id_customer;
    private String name;
    private String Last_name;

    private Long cell_phone;

    private String gender;


    public Cliente() {
    }

    public Cliente(int id_customer, String name, String last_name, Long cell_phone, String gender) {
        this.id_customer = id_customer;
        this.name = name;
        Last_name = last_name;
        this.cell_phone = cell_phone;
        this.gender = gender;
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
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
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
}

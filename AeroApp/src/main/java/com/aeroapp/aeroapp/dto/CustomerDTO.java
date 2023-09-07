package com.aeroapp.aeroapp.dto;

public class CustomerDTO {
    public int Id_customer;
    public String name;
    public String last_name;
    public long cell_phone;
    public String gender;

    public CustomerDTO() {
    }

    public CustomerDTO(int id_customers, String name, String last_name, long cell_phone, String gender) {
        Id_customer = id_customers;
        this.name = name;
        this.last_name = last_name;
        this.cell_phone = cell_phone;
        this.gender = gender;
    }

    public int getId_customers() {
        return Id_customer;
    }

    public void setId_customers(int id_customers) {
        Id_customer = id_customers;
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

    public long getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(long cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

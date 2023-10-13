package com.aeroapp.aeroapp.dto;

public class CustomerDTO {
    public String name;
    public String last_name;
    public long cell_phone;
    public String gender;
    private String reservation_number;
    private int customer_reservation;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String last_name, long cell_phone,
                       String gender, String reservation_number, int customer_reservation) {
        this.name = name;
        this.last_name = last_name;
        this.cell_phone = cell_phone;
        this.gender = gender;
        this.reservation_number = reservation_number;
        this.customer_reservation = customer_reservation;
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

    public String getReservation_number() {
        return reservation_number;
    }

    public void setReservation_number(String reservation_number) {
        this.reservation_number = reservation_number;
    }

    public int getCustomer_reservation() {
        return customer_reservation;
    }

    public void setCustomer_reservation(int customer_reservation) {
        this.customer_reservation = customer_reservation;
    }
}

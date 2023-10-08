package com.aeroapp.aeroapp.Security;

public class AuthCredentials {
    private String reservCode;
    private String last_name;

    public AuthCredentials() {
    }

    public AuthCredentials(String reservCode, String last_name) {
        this.reservCode = reservCode;
        this.last_name = last_name;
    }

    public String getReservCode() {
        return reservCode;
    }

    public void setReservCode(String reservCode) {
        this.reservCode = reservCode;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}

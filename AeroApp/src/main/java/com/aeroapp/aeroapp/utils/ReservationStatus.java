package com.aeroapp.aeroapp.utils;

public enum ReservationStatus {

    PENDING("Pending"), // Pendiente
    CONFIRMED("Confirmed"), // Confirmada
    CANCELED("Canceled"), // Cancelada
    COMPLETED("Completed"); // Completada

    private final String statusName;

    ReservationStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

}
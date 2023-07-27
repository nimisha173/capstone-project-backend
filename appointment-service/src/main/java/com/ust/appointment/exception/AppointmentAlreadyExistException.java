package com.ust.appointment.exception;

public class AppointmentAlreadyExistException extends RuntimeException {
    public AppointmentAlreadyExistException(String s) {
        super(s);
    }
}

package com.ust.appointment.exception;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(String s) {
        super(s);
    }
}

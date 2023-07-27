package com.ust.doctor.exception;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(String s) {
        super(s);
    }
}

package com.ust.doctor.exception;

public class DoctorAlreadyExistException extends RuntimeException {
    public DoctorAlreadyExistException(String s) {
        super(s);
    }
}

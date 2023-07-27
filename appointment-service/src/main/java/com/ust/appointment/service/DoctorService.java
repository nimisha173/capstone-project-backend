package com.ust.appointment.service;

import com.ust.appointment.entity.Doctor;

import java.util.Optional;

public interface DoctorService {

    Doctor findById (long doctorId);
}

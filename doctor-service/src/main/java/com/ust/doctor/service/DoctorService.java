package com.ust.doctor.service;

import com.ust.doctor.domain.Doctor;
import com.ust.doctor.exception.DoctorNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Doctor doctor) throws DoctorNotFoundException;
    void deleteDoctor(Doctor doctor) throws DoctorNotFoundException;
    Optional<Doctor> findDoctor(String doctorName) throws DoctorNotFoundException;
    Optional<Doctor> findById(Long doctorId) throws DoctorNotFoundException;
    List<Doctor> findByDept(String department) throws DoctorNotFoundException;
    List<Doctor> findAll() throws DoctorNotFoundException;
}

package com.ust.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.appointment.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByUserId(long userId);
    Optional<Appointment> findByUserId(long userId);
    Optional<Appointment> findByDoctorIdAndAppointmentDateAndAppointmentTime(long doctorId,LocalDate appointmentDate,LocalTime appointmentTime);

    List<Appointment> findAppointmentByUserIdAndDoctorId(long userId,long doctorId);

}

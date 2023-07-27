package com.ust.appointment.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.ust.appointment.entity.Appointment;
import com.ust.appointment.exception.AppointmentAlreadyExistException;
import com.ust.appointment.exception.AppointmentNotFoundException;

public interface AppointmentService {


	Appointment createAppointment(Appointment appointment);

	List<Appointment> viewAllAppointmentsByUser(Long userId) ;

	void deleteAppointment(Long appointmentId);

	Optional<Appointment> findByAppId(Long appointmentId) ;
	List<Appointment> findByuserIdAnddoctorId(long userId,long doctorId) ;
	Optional<Appointment> existingAppointment(long doctorId, LocalDate appointmentDate, LocalTime appointmentTime);
}

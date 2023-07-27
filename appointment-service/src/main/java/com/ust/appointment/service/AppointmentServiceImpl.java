package com.ust.appointment.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.ust.appointment.exception.AppointmentAlreadyExistException;
import com.ust.appointment.exception.AppointmentNotFoundException;
import com.ust.appointment.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.appointment.entity.Appointment;
import org.springframework.web.client.RestTemplate;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private AppointmentRepo appointmentRepo;
	@Autowired
	private DoctorServiceImpl doctorService;


	@Override
	public Appointment createAppointment(Appointment appointment) throws AppointmentAlreadyExistException  {
		var req=existingAppointment(appointment.getDoctorId(),appointment.getAppointmentDate(),appointment.getAppointmentTime());

		if(req.isEmpty()){
			return appointmentRepo.save(appointment);
			}
		else
			throw new AppointmentAlreadyExistException("Appointment already exist");
		}

	@Override
	public List<Appointment> viewAllAppointmentsByUser(Long userId) throws AppointmentNotFoundException {
		var response=appointmentRepo.findAllByUserId(userId);

		if(response.size()==0){
			throw new AppointmentNotFoundException("No Appointments Found");
		}
		return response;
	}

	@Override
	public void deleteAppointment(Long appointmentId) throws AppointmentNotFoundException {
		var res=appointmentRepo.findById(appointmentId);
		if(res.isEmpty()){
			throw new AppointmentNotFoundException("Appointment Not Found");
		}

		appointmentRepo.deleteById(appointmentId);
	}

	@Override
	public Optional<Appointment> findByAppId(Long appointmentId) throws AppointmentNotFoundException {
		var req=appointmentRepo.findById(appointmentId);
		if(req.isEmpty()){
			throw new AppointmentNotFoundException("Appointment Not Found");
		}

		return req;
	}

	@Override
	public List<Appointment> findByuserIdAnddoctorId(long userId,long doctorId) throws AppointmentNotFoundException {
		var req=appointmentRepo.findAppointmentByUserIdAndDoctorId(userId,doctorId);
		if(req.isEmpty()){
			throw new AppointmentNotFoundException("Appointment(s) not Found");
		}
		return req;
	}

	@Override
	public Optional<Appointment> existingAppointment(long doctorId, LocalDate appointmentDate, LocalTime appointmentTime) {

		return appointmentRepo.findByDoctorIdAndAppointmentDateAndAppointmentTime(doctorId,appointmentDate,appointmentTime);
	}


}

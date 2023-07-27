package com.ust.appointment.dto;

import com.ust.appointment.entity.Appointment;
import com.ust.appointment.entity.Doctor;
import com.ust.appointment.service.DoctorServiceImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DtoMapper {
    DoctorServiceImpl doctorServiceImpl;
    public DtoMapper(DoctorServiceImpl doctorServiceImpl) {
        this.doctorServiceImpl = doctorServiceImpl;
    }
    public Appointment createAppointment(RequestDto requestDto){
        Doctor doc=doctorServiceImpl.findById(requestDto.doctorId());
        Appointment appointment=new Appointment();
        appointment.setAppointmentDate(LocalDate.parse(requestDto.appointmentDate()));
        appointment.setUserId(requestDto.userId());
        appointment.setAppointmentTime(LocalTime.parse(requestDto.appointmentTime()));
        appointment.setDoctorId(requestDto.doctorId());
        appointment.setDoctorName(doc.getDoctorName());
        appointment.setDepartment(doc.getDepartment());
        appointment.setUserName(requestDto.userName());
        appointment.setDetails(requestDto.details());
        return appointment;
    }
    public Appointment convertToEntity(AppointmentDto appointmentDto){
        Appointment appointment=new Appointment();


        appointment.setAppointmentId(appointmentDto.appointmentId());
        appointment.setUserId(appointmentDto.userId());
        appointment.setAppointmentTime(appointmentDto.appointmentTime());
        appointment.setAppointmentDate(appointmentDto.appointmentDate());
        appointment.setDepartment(appointmentDto.department());
        appointment.setDetails(appointmentDto.details());
        appointment.setUserName(appointmentDto.userName());
        appointment.setDoctorId(appointmentDto.doctorId());
        appointment.setDoctorName(appointmentDto.doctorName());
        return  appointment;


    }


    public AppointmentDto convertToDto(Appointment appointment){

        return new AppointmentDto(
                appointment.getAppointmentId(),
                appointment.getUserId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getDoctorId(),
                appointment.getDoctorName(),
                appointment.getDepartment(),
                appointment.getUserName(),
                appointment.getDetails());
    }

}

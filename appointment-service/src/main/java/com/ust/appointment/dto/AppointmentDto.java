package com.ust.appointment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentDto(
        long appointmentId,
        long userId,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        long doctorId,
        String doctorName,
        String department,
        String userName,
         String details) {


}

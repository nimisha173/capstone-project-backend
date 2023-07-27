package com.ust.appointment.entity;

import java.time.LocalDate;
import java.time.LocalTime;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appointmentId;
	private long userId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate appointmentDate;
	@JsonFormat(pattern = "HH:mm:ss")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	private LocalTime appointmentTime;
	private long doctorId;
	private String doctorName;
	private String department;
	private String userName;
	private String details;

}

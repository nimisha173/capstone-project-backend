package com.ust.appointment.controller;

import com.ust.appointment.dto.AppointmentDto;
import com.ust.appointment.dto.DtoMapper;
import com.ust.appointment.dto.RequestDto;
import com.ust.appointment.entity.Appointment;
import com.ust.appointment.entity.Doctor;
import com.ust.appointment.service.AppointmentService;
import com.ust.appointment.service.DoctorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin
public class AppointmentController {


    private final AppointmentService appointmentService;
    private final DtoMapper dtoMapper;

    public AppointmentController(AppointmentService appointmentService, DtoMapper dtoMapper) {
        this.appointmentService = appointmentService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<AppointmentDto> create(@RequestBody RequestDto requestDto) {

        var request = dtoMapper.createAppointment(requestDto);
        var response = dtoMapper.convertToDto(appointmentService.createAppointment(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<List<AppointmentDto>> appointmentsByUser(@PathVariable long userId) {
        var appointmentList = appointmentService.viewAllAppointmentsByUser(userId);
        if(appointmentList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<AppointmentDto> appointmentDtoList = appointmentList.stream()
                .map(dtoMapper::convertToDto)
                .toList();
        return ResponseEntity.status(HttpStatus.FOUND).body(appointmentDtoList);

    }
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable long appointmentId){
        var res=appointmentService.findByAppId(appointmentId);
        if(res.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/find/{appointmentId}")
    public ResponseEntity<Appointment> findByAppointmentId(@PathVariable long appointmentId){
        var res=appointmentService.findByAppId(appointmentId);
        return res.map(appointment -> ResponseEntity.ok().body(appointment)).orElseGet(() -> ResponseEntity.noContent().build());
    }
    @GetMapping("/findaps/{userId}/{doctorId}")
    public ResponseEntity<List<Appointment>> findByUsIdAndDocId(@PathVariable long userId,@PathVariable long doctorId){

        var res=appointmentService.findByuserIdAnddoctorId(userId,doctorId);
        if (res.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(res);
    }
}

	



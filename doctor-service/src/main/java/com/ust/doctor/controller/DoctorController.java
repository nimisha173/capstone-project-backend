package com.ust.doctor.controller;

import com.ust.doctor.domain.Doctor;
import com.ust.doctor.dto.DoctorDto;
import com.ust.doctor.dto.RequestDto;
import com.ust.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    public Doctor convertToEntity(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorDto.doctorId());
        doctor.setDoctorName(doctorDto.doctorName());
        doctor.setDepartment(doctorDto.department());
        return doctor;
    }

    public DoctorDto convertToDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getDoctorId(),
                doctor.getDoctorName(),
                doctor.getDepartment()
        );}
    public Doctor createDoctorObject(RequestDto requestDto){
        Doctor doctor=new Doctor();
        doctor.setDoctorName(requestDto.doctorName());
        doctor.setDepartment(requestDto.department());
        return doctor;
    }
    @PostMapping("")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody RequestDto requestDto) {
        Doctor doc=createDoctorObject(requestDto);
        doctorService.createDoctor(doc);
        DoctorDto doctorDto1 = convertToDto(doc);
        return ResponseEntity.status(HttpStatus.OK).body(doctorDto1);
    }

    @PutMapping("")
    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody DoctorDto doctorDto) {
        final var doc = convertToEntity(doctorDto);
        if (doctorService.findById(doc.getDoctorId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        final var docDto = convertToDto(doctorService.updateDoctor(doc));
        return ResponseEntity.status(HttpStatus.OK).body(docDto);
    }

    @GetMapping("/{doctorName}")
    public ResponseEntity<DoctorDto> searchDoctorByName(@PathVariable String doctorName) {
        Optional<Doctor> doc = doctorService.findDoctor(doctorName);
        if (doc.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        final var docDto = convertToDto(doc.get());
        return ResponseEntity.status(HttpStatus.OK).body(docDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable long id) {
        final var doc = doctorService.findById(id);
        if (doc.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        doctorService.deleteDoctor(doc.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("/search/{department}")
    public ResponseEntity<List<DoctorDto>> searchDoctors(@PathVariable String department) {

        List<Doctor> doctorList = doctorService.findByDept(department);
        List<DoctorDto> doctorDtoList = doctorList.stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);

    }

    @GetMapping("/find/{doctorId}")
    public  ResponseEntity<Doctor> findDoctorById(@PathVariable Long doctorId){
        var doc=doctorService.findById(doctorId);
        if(doc.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return  ResponseEntity.status(HttpStatus.OK).body(doc.get());
    }
    @GetMapping("/findall")
    public  ResponseEntity<List<DoctorDto>> findAllDoctor(){
        List<DoctorDto> doctorDtoList =doctorService.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(doctorDtoList);

    }
}

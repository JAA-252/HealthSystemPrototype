package com.codecamp.NHS.controllers;

import com.codecamp.NHS.models.Doctor;
import com.codecamp.NHS.services.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @PostMapping("/{doctorId}/assign/{patientId}")
    public void assignPatient(@PathVariable Integer doctorId, @PathVariable Integer patientId) {
        doctorService.assignPatient(doctorId, patientId);
    }

    @PostMapping("/{doctorId}/unassign/{patientId}")
    public void unassignPatient(@PathVariable Integer doctorId, @PathVariable Integer patientId) {
        doctorService.unassignPatient(doctorId, patientId);
    }
}

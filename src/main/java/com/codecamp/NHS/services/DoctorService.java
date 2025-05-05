package com.codecamp.NHS.services;

import com.codecamp.NHS.models.Doctor;
import com.codecamp.NHS.models.Patient;
import com.codecamp.NHS.repositories.DoctorRepository;
import com.codecamp.NHS.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public DoctorService(DoctorRepository doctorRepo, PatientRepository patientRepo) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    public Doctor getDoctorById(Integer id) {
        return doctorRepo.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepo.findAll();
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    public void assignPatient(Integer doctorId, Integer patientId) {
        Doctor doctor = getDoctorById(doctorId);
        Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        doctor.assignPatient(patient);
        patientRepo.save(patient);
        doctorRepo.save(doctor);
    }

    public void unassignPatient(Integer doctorId, Integer patientId) {
        Doctor doctor = getDoctorById(doctorId);
        Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        doctor.unassignPatient(patient);
        patientRepo.save(patient);
        doctorRepo.save(doctor);
    }
}

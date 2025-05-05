package com.codecamp.NHS.services;

import com.codecamp.NHS.models.Patient;
import com.codecamp.NHS.models.Residence;
import com.codecamp.NHS.repositories.PatientRepository;
import com.codecamp.NHS.repositories.ResidenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenceService {

    private final ResidenceRepository residenceRepo;
    private final PatientRepository patientRepo;

    public ResidenceService(ResidenceRepository residenceRepo, PatientRepository patientRepo) {
        this.residenceRepo = residenceRepo;
        this.patientRepo = patientRepo;
    }

    public Residence getResidenceById(Integer id) {
        return residenceRepo.findById(id).orElseThrow(() -> new RuntimeException("Residence not found"));
    }

    public List<Residence> getAllResidences() {
        return residenceRepo.findAll();
    }

    public Residence createResidence(Residence residence) {
        return residenceRepo.save(residence);
    }

    public void assignPatient(Integer residenceId, Integer patientId) {
        Residence residence = getResidenceById(residenceId);
        Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        residence.assignPatient(patient);
        patientRepo.save(patient);
        residenceRepo.save(residence);
    }

    public void unassignPatient(Integer residenceId, Integer patientId) {
        Residence residence = getResidenceById(residenceId);
        Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));
        residence.unassignPatient(patient);
        patientRepo.save(patient);
        residenceRepo.save(residence);
    }
}

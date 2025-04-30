package com.codecamp.NHS.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doctor {

    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate birthDate;
    private Integer doctorId;
    private Integer residenceId;

    private List<Integer> patientIds; // IDs of assigned patients
    private static final int MAX_PATIENTS = 100; // Example limit

    public Doctor(Integer doctorId, String firstName, String lastName, Integer age, LocalDate birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.doctorId = doctorId;
        this.residenceId = residenceId;
        this.patientIds = new ArrayList<>();
    }

    public Integer getResidenceId() {
        return residenceId;
    }

    public Integer getAge() {
        return age;
    }

    public static int getMaxPatients() {
        return MAX_PATIENTS;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public List<Integer> getPatientIds() {
        return patientIds;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setResidenceId(Integer residenceId) {
        this.residenceId = residenceId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void assignPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        if (patient.isAssigned()) {
            throw new IllegalStateException("Patient already assigned to doctor: " + patient.getDoctorId());
        }
        if (patientIds.size() >= MAX_PATIENTS) {
            throw new IllegalStateException("Doctor has reached maximum patients.");
        }
        if(!Objects.equals(patient.getResidenceId(), getResidenceId())){
            throw new IllegalStateException("Not registered at the same location.");
        }

        // Assign the patient
        patient.setDoctorId(this.doctorId); // Sync Patient's doctorId
        patientIds.add(patient.getPatientId()); // Add to Doctor's list
    }

    public void unassignPatient(Patient patient) {
        if (patient != null && patientIds.contains(patient.getPatientId())) {
            patient.setDoctorId(null); // Clear Patient's doctorId
            patientIds.remove(patient.getPatientId());
        }
    }

}
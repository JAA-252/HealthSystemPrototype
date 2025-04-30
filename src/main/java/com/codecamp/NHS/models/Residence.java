package com.codecamp.NHS.models;

import java.util.List;

public class Residence {

    private Integer residenceId;
    private String town;
    private String city;

    private List<Integer> patientIds; // IDs of assigned patients
    private Integer maxCapacity = 100;

    public Residence(Integer residenceId, String town, String city, Integer maxCapacity){
        this.residenceId = residenceId;
        this.town = town;
        this.city = city;
        this.maxCapacity = maxCapacity;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Integer getResidenceId() {
        return residenceId;
    }

    public String getCity() {
        return city;
    }

    public String getTown() {
        return town;
    }

    public void setResidenceId(Integer residenceId) {
        this.residenceId = residenceId;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void assignPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        if (patient.isAssignedToResidence()) {
            throw new IllegalStateException("Patient already assigned to residence: " + patient.getResidenceId());
        }
        if (patientIds.size() >= maxCapacity) {
            throw new IllegalStateException("Doctor has reached maximum patients.");
        }

        // Assign the patient
        patient.setResidenceId(this.residenceId); // Sync Patient's doctorId
        patientIds.add(patient.getPatientId()); // Add to Doctor's list
    }

    public void unassignPatient(Patient patient) {
        if (patient != null && patientIds.contains(patient.getPatientId())) {
            patient.setResidenceId(null); // Clear Patient's doctorId
            patientIds.remove(patient.getPatientId());
        }
    }

}

package com.codecamp.NHS.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
        import java.util.ArrayList;
import java.util.List;

@Entity
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer residenceId;

    private String town;
    private String city;
    private Integer maxCapacity = 100;

    @OneToMany(mappedBy = "residenceId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Patient> patients = new ArrayList<>();

    public Residence() {}

    public Residence(Integer residenceId, String town, String city, Integer maxCapacity) {
        this.residenceId = residenceId;
        this.town = town;
        this.city = city;
        this.maxCapacity = maxCapacity;
    }

    public void assignPatient(Patient patient) {
        if (patient == null) throw new IllegalArgumentException("Patient cannot be null.");
        if (patient.getResidenceId() != null) throw new IllegalStateException("Already assigned to residence.");
        if (patients.size() >= maxCapacity) throw new IllegalStateException("Max capacity reached.");
        patient.setResidenceId(this);
        patients.add(patient);
    }

    public void unassignPatient(Patient patient) {
        if (patient != null && patients.contains(patient)) {
            patient.setResidenceId(null);
            patients.remove(patient);
        }
    }

    // Getters and setters

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public Integer getResidenceId() {
        return residenceId;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public String getCity() {
        return city;
    }

    public String getTown() {
        return town;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setResidenceId(Integer residenceId) {
        this.residenceId = residenceId;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void setTown(String town) {
        this.town = town;
    }

}

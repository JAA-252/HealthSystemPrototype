package com.codecamp.NHS.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate birthDate;
    private List<Integer> patientIds; // IDs of assigned patients

    @ManyToOne
    @JoinColumn(name = "residence_id")
    private Residence residence;

    @OneToMany(mappedBy = "doctorId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patients = new ArrayList<>();

    public static final int MAX_PATIENTS = 100;

    public Doctor() {
    }

    public Doctor(Integer doctorId, String firstName, String lastName, Integer age, LocalDate birthDate) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
    }

    public void assignPatient(Patient patient) {
        if (patient == null) throw new IllegalArgumentException("Patient cannot be null.");
        if (patient.getDoctorId() != null) throw new IllegalStateException("Patient already assigned.");
        if (patients.size() >= MAX_PATIENTS) throw new IllegalStateException("Max patients reached.");
        if (!Objects.equals(patient.getResidenceId(), this.residence)) throw new IllegalStateException("Residence mismatch.");
        patient.setDoctorId(this);
        patients.add(patient);
    }

    public void unassignPatient(Patient patient) {
        if (patient != null && patients.contains(patient)) {
            patient.setDoctorId(null);
            patients.remove(patient);
        }
    }

    public Integer getResidenceId(Doctor doctor) {
        return doctor.getResidence().getResidenceId();
    }

    public Residence getResidence() {
        return residence;
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

    public void setResidence(Residence residence) {
        this.residence = residence;
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
}

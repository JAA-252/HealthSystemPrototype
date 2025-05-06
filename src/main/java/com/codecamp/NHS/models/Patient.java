package com.codecamp.NHS.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctor doctorId;

    @ManyToOne
    @JoinColumn(name = "residence_id")
    @JsonBackReference
    private Residence residenceId;

    public Patient() {}

    public Patient(Integer patientId, Residence residence, String firstName, String lastName, LocalDate birthDate) {
        this.patientId = patientId;
        this.residenceId = residenceId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = calculateAge(birthDate, LocalDate.now());
    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return birthDate != null && currentDate != null ? Period.between(birthDate, currentDate).getYears() : 0;
    }

    public boolean isAssigned() {
        return doctorId != null;
    }

    public boolean isAssignedToResidence() {
        return residenceId != null;
    }

    // Getters and setters
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public Doctor getDoctorId() {
        return this.doctorId;
    }

    public Residence getResidenceId() {
        return this.residenceId;
    }

    // Private setter to enforce control via Doctor class
    void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public void setResidenceId(Residence residenceId) {
        this.residenceId = residenceId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}

package com.codecamp.NHS.models;

import java.time.LocalDate;
import java.time.Period;

public class Patient {

    private Integer patientId;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate birthDate;
    private Integer doctorId;
    private Integer residenceId;

    public Patient(Integer patientId, Integer residenceId, String firstName, String lastName, LocalDate birthDate, String primaryDoctorId) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.doctorId = doctorId;
        this.residenceId = residenceId;
        this.age = calculateAge(birthDate, LocalDate.now());
    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate != null && currentDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        }
        return 0;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public Integer getResidenceId() {
        return residenceId;
    }

    // Private setter to enforce control via Doctor class
    void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public void setResidenceId(Integer residenceId) {
        this.residenceId = residenceId;
    }

    // Called by Doctor to validate assignment
    public boolean isAssigned() {
        return doctorId != null;
    }

    public boolean isAssignedToResidence() {
        return residenceId != null;
    }

    public Integer getPatientId() {
        return patientId;
    }
}

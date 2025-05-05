package com.codecamp.NHS.repositories;

import com.codecamp.NHS.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}

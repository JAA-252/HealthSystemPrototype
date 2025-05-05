package com.codecamp.NHS.repositories;

import com.codecamp.NHS.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}

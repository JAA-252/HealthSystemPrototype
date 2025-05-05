package com.codecamp.NHS.repositories;

import com.codecamp.NHS.models.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidenceRepository extends JpaRepository<Residence, Integer> {
}

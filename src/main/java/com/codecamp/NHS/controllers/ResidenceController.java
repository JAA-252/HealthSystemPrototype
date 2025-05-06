package com.codecamp.NHS.controllers;

import com.codecamp.NHS.models.Residence;
import com.codecamp.NHS.services.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residences")
public class ResidenceController {

    @Autowired
    private final ResidenceService residenceService;

    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }

    @GetMapping("/all")
    public List<Residence> getAllResidences() {
        return residenceService.getAllResidences();
    }

    @GetMapping("/{id}")
    public Residence getResidenceById(@PathVariable Integer id) {
        return residenceService.getResidenceById(id);
    }

    @PostMapping
    public Residence createResidence(@RequestBody Residence residence) {
        return residenceService.createResidence(residence);
    }

    @PostMapping("/{residenceId}/assign/{patientId}")
    public void assignPatient(@PathVariable Integer residenceId, @PathVariable Integer patientId) {
        residenceService.assignPatient(residenceId, patientId);
    }

    @PostMapping("/{residenceId}/unassign/{patientId}")
    public void unassignPatient(@PathVariable Integer residenceId, @PathVariable Integer patientId) {
        residenceService.unassignPatient(residenceId, patientId);
    }
}

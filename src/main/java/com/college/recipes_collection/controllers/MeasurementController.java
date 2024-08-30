package com.college.recipes_collection.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.recipes_collection.dto.responses.MeasurementResponseDTO;
import com.college.recipes_collection.dto.requests.MeasurementRequestDTO;
import com.college.recipes_collection.services.MeasurementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    
    @Autowired
    private MeasurementService measurementService;

    @PostMapping
    public ResponseEntity<Void> createMeasurement(@Valid @RequestBody MeasurementRequestDTO request) {
        measurementService.createMeasurement(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasurementResponseDTO> getMeasurementById(@PathVariable int id) {
        MeasurementResponseDTO measurement = measurementService.getMeasurementById(id);
        return ResponseEntity.ok(measurement);
    }

    @GetMapping
    public ResponseEntity<List<MeasurementResponseDTO>> getAllMeasurements() {
        List<MeasurementResponseDTO> measurements = measurementService.getAllMeasurements();
        return ResponseEntity.ok(measurements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeasurementResponseDTO> updateMeasurement(@PathVariable int id, @RequestBody MeasurementRequestDTO request) {
        MeasurementResponseDTO updatedMeasurement = measurementService.updateMeasurement(id, request);
        return ResponseEntity.ok(updatedMeasurement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        measurementService.deleteMeasurement(id);
        return ResponseEntity.noContent().build();
    }
}
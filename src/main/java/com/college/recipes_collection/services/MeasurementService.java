package com.college.recipes_collection.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.responses.MeasurementResponseDTO;
import com.college.recipes_collection.models.Measurement;
import com.college.recipes_collection.repositories.MeasurementRepository;
import com.college.recipes_collection.dto.requests.MeasurementRequestDTO;

@Service
public class MeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;

    public void createMeasurement(MeasurementRequestDTO request) {
        Measurement measurement = new Measurement();
        
        measurement.setName(request.name());
        measurement.setDescription(request.description());

        measurementRepository.save(measurement);
    }

    public MeasurementResponseDTO getMeasurementById(int id) {
        Measurement measurement = measurementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Measurement not found"));

        return new MeasurementResponseDTO(
            measurement.getId(), 
            measurement.getName(), 
            measurement.getDescription()
        );
    }

    public List<MeasurementResponseDTO> getAllMeasurements() {
        return measurementRepository.findAll().stream()
            .map(measurement -> new MeasurementResponseDTO(
                measurement.getId(), 
                measurement.getName(), 
                measurement.getDescription()
            ))
            .collect(Collectors.toList());
    }

    public MeasurementResponseDTO updateMeasurement(int id, MeasurementRequestDTO request) {
        Measurement updatedMeasurement = measurementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Measurement not found"));

        updatedMeasurement.setName(request.name());
        updatedMeasurement.setDescription(request.description());

        Measurement savedMeasurement = measurementRepository.save(updatedMeasurement);

        return new MeasurementResponseDTO(
            savedMeasurement.getId(),
            savedMeasurement.getName(),
            savedMeasurement.getDescription()
        );
    }

    public void deleteMeasurement(int id) {
        if (measurementRepository.existsById(id)) {
            measurementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Measurement not found");
        }
    }
}

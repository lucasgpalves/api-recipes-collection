package com.college.recipes_collection.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.recipes_collection.dto.requests.JobReferenceRequestDTO;
import com.college.recipes_collection.dto.responses.JobReferenceResponseDTO;
import com.college.recipes_collection.services.JobReferenceService;

@RestController
@RequestMapping("/references")
public class JobReferenceController {
        
    @Autowired
    private JobReferenceService jobReferenceService;
    
    @PostMapping
    public ResponseEntity<Void> createJobReference(@RequestBody JobReferenceRequestDTO request) {
        jobReferenceService.createJobReference(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<JobReferenceResponseDTO>> getAllJobReferences() {
        List<JobReferenceResponseDTO> jobReferences = jobReferenceService.getAllJobReferences();
        return ResponseEntity.ok(jobReferences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobReferenceResponseDTO> getJobReferenceById(@PathVariable Long id) {
        JobReferenceResponseDTO jobReference = jobReferenceService.getJobReferenceById(id);
        return ResponseEntity.ok(jobReference);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateJobReferenceById(@PathVariable Long id, @RequestBody JobReferenceRequestDTO request) {
        jobReferenceService.updateJobReferenceById(request, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobReferenceById(@PathVariable Long id) {
        jobReferenceService.deleteJobReferenceById(id);
        return ResponseEntity.noContent().build();
    }
}

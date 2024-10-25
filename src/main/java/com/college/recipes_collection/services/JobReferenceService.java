package com.college.recipes_collection.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.recipes_collection.dto.requests.JobReferenceRequestDTO;
import com.college.recipes_collection.dto.responses.JobReferenceResponseDTO;
import com.college.recipes_collection.dto.responses.UserResponseDTO;
import com.college.recipes_collection.models.JobReference;
import com.college.recipes_collection.models.User;
import com.college.recipes_collection.repositories.JobReferenceRepository;

@Service
public class JobReferenceService {
    
    @Autowired
    private JobReferenceRepository jobReferenceRepository;

    @Autowired
    private UserService userService;
    
    public void createJobReference(JobReferenceRequestDTO request) {
        JobReference createdJobReference = new JobReference();

        saveJobReference(createdJobReference, request);
    }

    public List<JobReferenceResponseDTO> getAllJobReferences() {
        return jobReferenceRepository.findAll().stream()
            .map(jobReference -> new JobReferenceResponseDTO(
                jobReference.getRestaurant().getName(),
                jobReference.getStartsAt(),
                jobReference.getEndsAt(),
                jobReference.getContactNumber(),
                mapUserResponseDTO(jobReference.getUser())
            )).collect(Collectors.toList());
    }

    public JobReferenceResponseDTO getJobReferenceById(Long id) {
        JobReference jobReference = findById(id);

        return new JobReferenceResponseDTO(
            jobReference.getRestaurant().getName(),
            jobReference.getStartsAt(),
            jobReference.getEndsAt(),
            jobReference.getContactNumber(),
            mapUserResponseDTO(jobReference.getUser())
        );
    }

    public void updateJobReferenceById(JobReferenceRequestDTO request, Long id) {
        JobReference updatedJobReference = findById(id);

        saveJobReference(updatedJobReference, request);
    }

    public void deleteJobReferenceById(Long id) {
        if(jobReferenceRepository.existsById(id)) {
            jobReferenceRepository.deleteById(id);
        } else {
            throw new RuntimeException("Job Reference not found");
        }
    }

    private void saveJobReference(JobReference jobReference, JobReferenceRequestDTO request) {
        jobReference.setStartsAt(null);
        jobReference.setEndsAt(null);
        jobReference.setContactNumber(null);
        jobReference.setRestaurant(null);
        jobReference.setUser(null);

        jobReferenceRepository.save(jobReference);
    }

    private JobReference findById(Long id) {
        return jobReferenceRepository.findById(id).orElseThrow(() -> new RuntimeException("Job Reference not found"));
    } 

    private UserResponseDTO mapUserResponseDTO(User user) {
        if (user == null) throw new RuntimeException("User not found");

        return userService.getUserById(user.getId());
    }
}

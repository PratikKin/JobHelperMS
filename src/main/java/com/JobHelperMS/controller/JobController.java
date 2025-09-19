package com.JobHelperMS.controller;

import com.JobHelperMS.constants.JobStatus;
import com.JobHelperMS.model.Job;
import com.JobHelperMS.service.JobService;
import com.JobHelperMS.utils.job.JobRequest;
import com.JobHelperMS.utils.job.JobResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JobController {

    private final JobService jobService;


    @PostMapping("/create")
    public ResponseEntity<JobResponse> createJob(@RequestBody JobRequest request) {
        if (!"RECRUITER".equalsIgnoreCase(String.valueOf(request.getRequestInfo().getRole()))) {
            System.out.println("Unauthorized attempt to create job by user with role: " + request.getRequestInfo().getRole());
            return ResponseEntity.status(403).build(); // Forbidden
        }
        Job createdJob = jobService.createJob(request);
        JobResponse response = new JobResponse();
        response.setJob(createdJob);
        response.setCreatedBy(request.getRequestInfo().getId());
        response.setStatus(JobStatus.ACTIVE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }
}

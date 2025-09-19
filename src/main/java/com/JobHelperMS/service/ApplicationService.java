package com.JobHelperMS.service;

import com.JobHelperMS.constants.Role;
import com.JobHelperMS.constants.Status;
import com.JobHelperMS.dto.ApplicationDTO;
import com.JobHelperMS.dto.JobDTO;
import com.JobHelperMS.model.Application;
import com.JobHelperMS.model.Job;
import com.JobHelperMS.repository.ApplicationRepository;
import com.JobHelperMS.repository.JobRepository;
import com.JobHelperMS.utils.application.ApplicationBody;
import com.JobHelperMS.utils.application.ApplicationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationService {

    @Autowired
    private final ApplicationRepository applicationRepository;
    @Autowired
    private final JobRepository jobRepository;


    public Application createApplication(ApplicationRequest request) {
        // ✅ Verify requester has role CANDIDATE
        Role requesterRole = request.getRequestInfo().getRole();
        String requesterId = request.getRequestInfo().getId();

        if (!Role.CANDIDATE.equals(requesterRole)) {
            throw new RuntimeException("Only candidates can apply for jobs.");
        }

        ApplicationBody appBody = request.getApplication();


        // ✅ Fetch job by jobId
        Job job = jobRepository.findById(appBody.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found: " + appBody.getJobId()));

        // ✅ Build ApplicationDTO
        ApplicationDTO dto = new ApplicationDTO();

        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setCompanyName(job.getCompanyName());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setQualification(job.getQualification());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setSalary(job.getSalary());
        jobDTO.setExperience(job.getExperience());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setPostedDate(job.getPostedDate());
        jobDTO.setLastUpdated(job.getLastUpdated());

        dto.setCandId(requesterId);
        dto.setJobDTO(jobDTO);
        dto.setStatus(Status.valueOf(appBody.getStatus()));
        dto.setAppliedDate(appBody.getAppliedDate() != null ? appBody.getAppliedDate() : String.valueOf(LocalDateTime.now()));
        dto.setLastUpdated(String.valueOf(LocalDateTime.now()));

        // ✅ Generate Application ID
        String newAppId = generateNewApplicationId();

        // ✅ Create Application entity
        Application application = new Application();
        application.setId(newAppId);
        application.setCandId(requesterId);
        application.setJob(job);
        application.setStatus(dto.getStatus());
        application.setAppliedDate(dto.getAppliedDate());
        application.setLastUpdated(dto.getLastUpdated());

        return applicationRepository.save(application);
    }

    private String generateNewApplicationId() {
        Optional<Application> lastApp = applicationRepository.findAll().stream()
                .max((a, b) -> {
                    int numA = Integer.parseInt(a.getId().replace("APP_", ""));
                    int numB = Integer.parseInt(b.getId().replace("APP_", ""));
                    return Integer.compare(numA, numB);
                });

        if (lastApp.isEmpty()) {
            return "APP_1";
        }

        String lastId = lastApp.get().getId(); // e.g., APP_12
        int num = Integer.parseInt(lastId.replace("APP_", ""));
        return "APP_" + (num + 1);
    }
}

package com.JobHelperMS.controller;


import com.JobHelperMS.model.Application;
import com.JobHelperMS.service.ApplicationService;
import com.JobHelperMS.utils.application.ApplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/create")
    public ResponseEntity<?> createApplication(@RequestBody ApplicationRequest request) {
        Application application = applicationService.createApplication(request);

        return ResponseEntity.ok().body(
                new ApplicationResponse(application, "created")
        );
    }

    // Inner Response DTO
    record ApplicationResponse(Application application, String status) {}
}

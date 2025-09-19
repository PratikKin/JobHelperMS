package com.JobHelperMS.dto;

import com.JobHelperMS.constants.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private String id = null; // application id
    private String candId;
    private JobDTO jobDTO;

    @Enumerated(EnumType.STRING)
    private Status status; // APPLIED, UNDER_REVIEW, INTERVIEW_SCHEDULED, OFFERED, REJECTED, HIRED.

//    private List<Feedback> feedbacks;
    private String appliedDate;
    private String lastUpdated;
}
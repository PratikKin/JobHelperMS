package com.JobHelperMS.model;

import com.JobHelperMS.constants.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="applications")
public class Application {
    @Id
    @Column(name = "applicationId", nullable = false, unique = true)
    private String id ; // application id

    private String candId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", referencedColumnName = "jobId")
    private Job job;

    @Enumerated(EnumType.STRING)
    private Status status; // APPLIED, UNDER_REVIEW, INTERVIEW_SCHEDULED, OFFERED, REJECTED, HIRED.

//    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<FeedbackDTO> feedbacks;

    private String appliedDate;
    private String lastUpdated;
}


package com.JobHelperMS.utils.job;

import com.JobHelperMS.model.Job;
import com.JobHelperMS.constants.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
    private Job job;
    private String createdBy;
    private JobStatus status;
}


package com.JobHelperMS.utils.job;

import com.JobHelperMS.utils.RequestInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {
    private RequestInfo requestInfo;
    private JobDetails job;
}
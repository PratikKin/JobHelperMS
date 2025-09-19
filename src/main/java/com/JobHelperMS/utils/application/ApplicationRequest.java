package com.JobHelperMS.utils.application;


import com.JobHelperMS.utils.RequestInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequest {
    @JsonProperty("RequestInfo")
    private RequestInfo RequestInfo;
    @JsonProperty("Application")
    private ApplicationBody Application;
}

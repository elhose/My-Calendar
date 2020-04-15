package com.js.calendar.controller;

import com.js.calendar.dto.job.JobDTO;
import com.js.calendar.dto.job.JobShortDTO;
import com.js.calendar.dto.job.JobUpdateDTO;
import com.js.calendar.entities.Job;
import com.js.calendar.mappers.JobMapper;
import com.js.calendar.service.JobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jobs")
public class JobController extends BaseController<JobDTO, JobShortDTO, JobUpdateDTO, Job>{

    private JobMapper mapper;
    private JobService service;

    public JobController(JobMapper mapper, JobService service) {
        super(mapper, service);
        this.mapper = mapper;
        this.service = service;
    }
}

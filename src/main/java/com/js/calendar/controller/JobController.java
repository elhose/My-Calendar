package com.js.calendar.controller;

import com.js.calendar.mappers.JobMapper;
import com.js.calendar.service.JobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jobs")
public class JobController extends BaseController{

    private JobMapper mapper;
    private JobService service;

    public JobController(JobMapper mapper, JobService service) {
        super(mapper, service);
        this.mapper = mapper;
        this.service = service;
    }
}

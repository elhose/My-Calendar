package com.js.calendar.controller;

import com.js.calendar.entities.Job;
import com.js.calendar.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    //testing purposes

    @GetMapping("/save")
    public ResponseEntity<Job> saveJob() {

        Job job = Job.builder().days("test").createdDate(Timestamp.valueOf(LocalDateTime.now())).build();

        jobService.addJob(job);

        return new ResponseEntity<>(job, HttpStatus.OK);
    }

}

package com.js.calendar.controller;

import com.js.calendar.entities.Job;
import com.js.calendar.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    //testing purposes

    @GetMapping
    public ResponseEntity<Iterable<Job>> getJobs() {
        return new ResponseEntity<>(jobService.getJobs(), HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJob(@PathVariable("jobId") Integer jobId) {
        Optional<Job> job = jobService.getJob(jobId);
        return job.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Job(), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addJob(@RequestBody Job job) {
        jobService.addJob(job);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity updateJobById(@PathVariable("jobId") Integer jobId, @RequestBody Job job) {
        jobService.updateJob(jobId, job);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity deleteJobById(@PathVariable("jobId") Integer jobId) {
        jobService.deleteJob(jobId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        //add EXCEPTION HANDLING
    }

}

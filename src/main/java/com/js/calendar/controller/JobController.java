package com.js.calendar.controller;

import com.js.calendar.dto.JobDTO;
import com.js.calendar.dto.JobUpdateDTO;
import com.js.calendar.entities.Job;
import com.js.calendar.mappers.JobMapper;
import com.js.calendar.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jobs")
public class JobController {

    private JobMapper mapper;
    private JobService service;

    public JobController(JobMapper mapper, JobService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<JobDTO>> getJobs() {
        Iterable<Job> jobs = service.getEntities();
        List<JobDTO> dtos = new ArrayList<>();

        jobs.forEach(job -> dtos.add(mapper.mapJobToJobDTO(job)));

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDTO> getJob(@PathVariable("jobId") Long jobId) {
        Optional<Job> job = service.getEntity(jobId);
        return job.map(value -> new ResponseEntity<>(mapper.mapJobToJobDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new JobDTO(), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity addJob(@RequestBody JobUpdateDTO jobUpdateDTO) {
        Job job = mapper.mapJobUpdateDtoToJob(jobUpdateDTO);
        service.addEntity(job);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity updateJobById(@PathVariable("jobId") Long jobId, @RequestBody JobUpdateDTO jobUpdateDTO) {
        Job job = mapper.mapJobUpdateDtoToJob(jobUpdateDTO);
        service.updateEntity(jobId, job);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity deleteJobById(@PathVariable("jobId") Long jobId) {
        service.deleteEntity(jobId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
